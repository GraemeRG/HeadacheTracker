package com.sbnl.headachetracker.modules

import androidx.room.Room
import com.sbnl.headachetracker.database.HeadacheDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room
            .databaseBuilder(
                androidApplication().applicationContext,
                HeadacheDatabase::class.java,
                "sbnl-headachetracker-db"
            )
            .build()
    }
}