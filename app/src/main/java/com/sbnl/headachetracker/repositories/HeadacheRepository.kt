package com.sbnl.headachetracker.repositories

import com.sbnl.headachetracker.DateTimeProvider
import com.sbnl.headachetracker.database.HeadacheDatabase
import com.sbnl.headachetracker.database.HeadacheObject
import com.sbnl.headachetracker.database.HeadacheStartPeriod
import org.joda.time.DateTime

interface HeadacheRepository {

    suspend fun storeHeadacheInfo(startPeriod: HeadacheStartPeriod)
    suspend fun getAllHeadacheData(): List<Headache>
    suspend fun getAllHeadachesSinceStartOfDay(): List<Headache>
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

    private fun List<HeadacheObject>.mapToDataList(): List<Headache> =
        map {
            Headache(
                dateRecorded = DateTime(it.dateRecorded),
                timeNoticed = HeadacheStartPeriod.forStartPeriod(it.timeNoticed)
            )
        }
}

data class Headache(
    val dateRecorded: DateTime,
    val timeNoticed: HeadacheStartPeriod
)