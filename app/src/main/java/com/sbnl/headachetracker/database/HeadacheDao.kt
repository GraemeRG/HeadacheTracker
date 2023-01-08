package com.sbnl.headachetracker.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface HeadacheDao {
    @Query("SELECT * FROM headacheobject")
    suspend fun getAll(): List<HeadacheObject>

    @Insert
    suspend fun insertAll(vararg headache: HeadacheObject)
}