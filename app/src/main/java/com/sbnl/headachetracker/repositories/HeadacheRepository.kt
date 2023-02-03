package com.sbnl.headachetracker.repositories

import com.sbnl.headachetracker.utils.datetime.DateTimeProvider
import com.sbnl.headachetracker.database.HeadacheDatabase
import com.sbnl.headachetracker.database.headache.HeadacheObject
import com.sbnl.headachetracker.database.headache.HeadacheStartPeriod
import com.sbnl.headachetracker.features.headachequestionnaire.HeadacheReport
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.transform
import org.joda.time.DateTime

interface HeadacheRepository {

    suspend fun storeHeadacheInfo(startPeriod: HeadacheStartPeriod)
    suspend fun storeHeadacheInfo(report: HeadacheReport)
    suspend fun getAllHeadacheData(): List<Headache>
    suspend fun getAllHeadachesSinceStartOfDay(): List<Headache>
    suspend fun flowOfHeadachesSinceStartOfDay(): Flow<List<Headache>>
    suspend fun getAllMedicationTakenForHeadache(headacheId: Long): List<RecordedMedication>
    suspend fun updateHeadacheWithClearedTime(headacheId: Long, headacheClearTime: Long)
    suspend fun updateHeadacheWithMedication(
        headacheId: Long,
        medicationId: Long,
        medicationTakenTime: Long
    )
}

class HeadacheRepositoryImpl(
    private val database: HeadacheDatabase,
    private val dateTimeProvider: DateTimeProvider
) : HeadacheRepository {

    override suspend fun storeHeadacheInfo(startPeriod: HeadacheStartPeriod) {
        database
            .headacheDao()
            .insertAll(
                HeadacheObject(
                    dateRecorded = dateTimeProvider.nowUtcInMillis(),
                    timeNoticed = startPeriod.headachePeriod
                )
            )
    }

    override suspend fun storeHeadacheInfo(report: HeadacheReport) {
        val recordedTime = dateTimeProvider.nowUtcInMillis()
        database
            .headacheDao()
            .insertAll(
                HeadacheObject(
                    dateRecorded = recordedTime,
                    timeNoticed = report.startPeriod.headachePeriod,
                    monitoredPainLevel = listOf(
                        PainLevelOverHeadache(
                            report.initialPainLevel,
                            recordedTime
                        )
                    )
                )
            )
    }

    override suspend fun getAllHeadacheData(): List<Headache> =
        database
            .headacheDao()
            .getAll()
            .mapToDataList()

    override suspend fun getAllHeadachesSinceStartOfDay(): List<Headache> =
        database
            .headacheDao()
            .getAllAfterTimestamp(dateTimeProvider.startOfCurrentDayInMillis())
            .mapToDataList()

    override suspend fun flowOfHeadachesSinceStartOfDay(): Flow<List<Headache>> =
        database
            .headacheDao()
            .flowAllHeadachesAfterTimestamp(dateTimeProvider.startOfCurrentDayInMillis())
            .transform { headaches -> emit(headaches.mapToDataList()) }

    override suspend fun updateHeadacheWithClearedTime(headacheId: Long, headacheClearTime: Long) {
        database
            .headacheDao()
            .addTimeClearedToHeadache(headacheId, headacheClearTime)
    }

    override suspend fun updateHeadacheWithMedication(
        headacheId: Long,
        medicationId: Long,
        medicationTakenTime: Long
    ) {
        getAllMedicationTakenForHeadache(headacheId)
            .toMutableList()
            .apply { add(RecordedMedication(id = medicationId, timeTaken = medicationTakenTime)) }
            .apply {
                database
                    .headacheDao()
                    .updateHeadacheWithMedicationTaken(headacheId, this)
            }

    }

    override suspend fun getAllMedicationTakenForHeadache(headacheId: Long): List<RecordedMedication> =
        database
            .headacheDao()
            .getHeadacheWithDateRecorded(headacheId)
            .lastOrNull()
            ?.medicationTaken
            ?: emptyList()

    private fun List<HeadacheObject>.mapToDataList(): List<Headache> =
        map { headache ->
            Headache(
                dateRecorded = DateTime(headache.dateRecorded),
                timeNoticed = HeadacheStartPeriod.forStartPeriod(headache.timeNoticed),
                timeCleared = headache.timeCleared?.let { DateTime(it) },
                painLevelOverTime = headache.monitoredPainLevel.map { PainLevelOverHeadache(it.painLevel, it.timeRecorded) }
            )
        }
}

data class Headache(
    val dateRecorded: DateTime,
    val timeNoticed: HeadacheStartPeriod,
    val timeCleared: DateTime? = null,
    val medicationRecorded: List<RecordedMedication> = emptyList(),
    val painLevelOverTime: List<PainLevelOverHeadache> = emptyList()
)

data class RecordedMedication(
    val id: Long,
    val timeTaken: Long
)

data class PainLevelOverHeadache(
    val painLevel: Int,
    val timeRecorded: Long
)