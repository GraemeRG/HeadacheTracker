package com.sbnl.headachetracker.features.medicationquestionnaire.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.sbnl.headachetracker.features.medicationquestionnaire.MedicationQuestionnaireViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun MedicationQuestionnaire(returnToHomeScreen: () -> Unit, viewModel: MedicationQuestionnaireViewModel = getViewModel()) {
    val shouldReturnToHomeScreen = viewModel.returnToHomeScreen.value
    if(shouldReturnToHomeScreen >= 0) {
        returnToHomeScreen()
    }

    MedicationTakenQuestion(viewModel)

    viewModel.onEnterQuestionnaire()
}

@Preview
@Composable
fun MedicationTakenPreview() {
    MedicationQuestionnaire({})
}