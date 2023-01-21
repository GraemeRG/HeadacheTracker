package com.sbnl.headachetracker.modules

import com.sbnl.headachetracker.features.headachequestionnaire.HeadacheQuestionnaireCompletedUseCase
import com.sbnl.headachetracker.features.headachequestionnaire.HeadacheQuestionnaireViewModel
import com.sbnl.headachetracker.features.homescreen.ReportHeadacheClearedUseCase
import com.sbnl.headachetracker.features.homescreen.ReportHeadacheGoneViewModel
import com.sbnl.headachetracker.repositories.HeadacheRepository
import com.sbnl.headachetracker.repositories.HeadacheRepositoryImpl
import com.sbnl.headachetracker.repositories.MedicationRepository
import com.sbnl.headachetracker.repositories.MedicationRepositoryImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val reportHeadacheModule = module {
    //-- Report Headache --
    viewModel { HeadacheQuestionnaireViewModel(completedUseCase = get()) }

    factory { HeadacheQuestionnaireCompletedUseCase(headacheRepository = get()) }

    //-- Report Headache Gone --
    viewModel { ReportHeadacheGoneViewModel(useCase = get()) }

    factory {
        ReportHeadacheClearedUseCase(
            dateProvider = get(),
            headacheRepo = get()
        )
    }
}