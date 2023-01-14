package com.sbnl.headachetracker.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface HeadacheDao {
    @Query("SELECT * FROM headaches")
    suspend fun getAll(): List<HeadacheObject>

    @Query("SELECT * FROM headaches WHERE dateRecorded > :timestamp")
    suspend fun getAllAfterTimestamp(timestamp: Long): List<HeadacheObject>

    @Query("SELECT * FROM headaches WHERE dateRecorded=:dateRecorded")
    suspend fun getHeadacheWithDateRecorded(dateRecorded: Long): List<HeadacheObject>

    @Query("UPDATE headaches SET timeCleared = :timeCleared WHERE dateRecorded =:id")
    suspend fun addTimeClearedToHeadache(id: Long, timeCleared: Long)

    @Insert
    suspend fun insertAll(vararg headache: HeadacheObject)
}