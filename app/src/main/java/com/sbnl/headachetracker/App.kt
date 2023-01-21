package com.sbnl.headachetracker

import android.app.Application
import androidx.room.Room
import com.sbnl.headachetracker.database.HeadacheDatabase
import com.sbnl.headachetracker.features.homescreen.HomeScreenViewModel
import com.sbnl.headachetracker.core.CurrentHeadacheInfoLoadingUseCase
import com.sbnl.headachetracker.modules.*
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@App)
            modules(
                reportHeadacheModule,
                repositoriesModule,
                reportMedicationModule,
                databaseModule,
                homeScreenModule,
                utilsModule
            )
        }
    }
}