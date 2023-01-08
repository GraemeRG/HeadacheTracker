package com.sbnl.headachetracker.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [HeadacheObject::class], version = 1)
abstract class HeadacheDatabase : RoomDatabase() {
    abstract fun headacheDao(): HeadacheDao
}