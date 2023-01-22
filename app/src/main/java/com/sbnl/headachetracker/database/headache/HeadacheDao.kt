package com.sbnl.headachetracker.database.headache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.sbnl.headachetracker.repositories.RecordedMedication
import kotlinx.coroutines.flow.Flow

@Dao
interface HeadacheDao {
    @Query("SELECT * FROM headaches")
    suspend fun getAll(): List<HeadacheObject>

    @Query("SELECT * FROM headaches WHERE dateRecorded > :timestamp")
    suspend fun getAllAfterTimestamp(timestamp: Long): List<HeadacheObject>

    @Query("SELECT * FROM headaches WHERE dateRecorded > :timestamp")
    fun flowAllHeadachesAfterTimestamp(timestamp: Long): Flow<List<HeadacheObject>>

    @Query("SELECT * FROM headaches WHERE dateRecorded=:dateRecorded")
    suspend fun getHeadacheWithDateRecorded(dateRecorded: Long): List<HeadacheObject>

    @Query("UPDATE headaches SET timeCleared = :timeCleared WHERE dateRecorded =:id")
    suspend fun addTimeClearedToHeadache(id: Long, timeCleared: Long)

    @Query("UPDATE headaches SET medicationTaken = :medicationTaken WHERE dateRecorded=:id")
    suspend fun updateHeadacheWithMedicationTaken(id: Long, medicationTaken: List<RecordedMedication>)

    @Insert
    suspend fun insertAll(vararg headache: HeadacheObject)
}