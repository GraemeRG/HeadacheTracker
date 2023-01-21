package com.sbnl.headachetracker.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sbnl.headachetracker.database.converters.DataConverters
import com.sbnl.headachetracker.database.headache.HeadacheDao
import com.sbnl.headachetracker.database.headache.HeadacheObject
import com.sbnl.headachetracker.database.medication.MedicationDao
import com.sbnl.headachetracker.database.medication.MedicationObject

@Database(entities = [HeadacheObject::class, MedicationObject::class], version = 1)
@TypeConverters(DataConverters::class)
abstract class HeadacheDatabase : RoomDatabase() {

    abstract fun headacheDao(): HeadacheDao

    abstract fun medicationDao(): MedicationDao
}