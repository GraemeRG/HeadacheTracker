package com.sbnl.headachetracker.modules

import com.sbnl.headachetracker.features.medicationquestionnaire.MedicationQuestionnaireUseCase
import com.sbnl.headachetracker.features.medicationquestionnaire.MedicationQuestionnaireViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val reportMedicationModule = module {
    viewModel {
        MedicationQuestionnaireViewModel(
            headacheUseCase = get(),
            questionnaireUseCase = get()
        )
    }

    factory {
        MedicationQuestionnaireUseCase(
            dateTimeProvider = get(),
            headacheRepository = get(),
            medicationRepository = get()
        )
    }
}