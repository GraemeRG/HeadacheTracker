package com.sbnl.headachetracker.database.medication

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.sbnl.headachetracker.database.headache.HeadacheObject

@Dao
interface MedicationDao {
    @Query("SELECT * FROM medication")
    suspend fun getAll(): List<MedicationObject>

    @Query("SELECT * FROM medication WHERE medicationId=:id")
    suspend fun getMedicationById(id: Long): List<MedicationObject>

    @Query("SELECT * FROM medication WHERE name=:name")
    suspend fun getMedicationByName(name: String): List<MedicationObject>

    @Insert
    suspend fun insertAll(vararg medication: MedicationObject)
}