package com.sbnl.headachetracker

import android.app.Application
import androidx.room.Room
import com.sbnl.headachetracker.database.HeadacheDatabase
import com.sbnl.headachetracker.features.HeadacheQuestionnaireViewModel
import com.sbnl.headachetracker.features.homescreen.HomeScreenDataUseCase
import com.sbnl.headachetracker.features.homescreen.HomeScreenViewModel
import com.sbnl.headachetracker.features.QuestionnaireCompletedUseCase
import com.sbnl.headachetracker.features.homescreen.ReportHeadacheClearedUseCase
import com.sbnl.headachetracker.features.homescreen.ReportHeadacheGoneViewModel
import com.sbnl.headachetracker.repositories.HeadacheRepository
import com.sbnl.headachetracker.repositories.HeadacheRepositoryImpl
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
                module {
                    viewModel { HomeScreenViewModel(useCase = get()) }

                    viewModel { HeadacheQuestionnaireViewModel(completedUseCase = get()) }

                    viewModel { ReportHeadacheGoneViewModel(useCase = get()) }

                    factory { ReportHeadacheClearedUseCase(dateProvider = get(), headacheRepo = get()) }

                    factory { HomeScreenDataUseCase(headacheRepo = get()) }

                    factory { QuestionnaireCompletedUseCase(headacheRepository = get()) }

                    factory<HeadacheRepository> { HeadacheRepositoryImpl(database = get(), dateTimeProvider = get()) }

                    factory { DateTimeProvider() }

                    single {
                        Room.databaseBuilder(
                            applicationContext,
                            HeadacheDatabase::class.java,
                            "sbnl-headachetracker-db"
                        ).build()
                    }
                }
            )
        }
    }
}