package com.sbnl.headachetracker.features.medicationquestionnaire.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.sbnl.headachetracker.features.medicationquestionnaire.CUSTOM_ANSWER_ID
import com.sbnl.headachetracker.features.medicationquestionnaire.MedicationQuestionnaireViewModel
import com.sbnl.headachetracker.sharedcomposables.RadioAnswer
import com.sbnl.headachetracker.sharedcomposables.RadioEditEntry
import com.sbnl.headachetracker.sharedcomposables.VerticalSpacer
import com.sbnl.headachetracker.ui.theme.Typography

@Composable
fun MedicationTakenQuestion(viewModel: MedicationQuestionnaireViewModel) {

    val answerState = viewModel.answerSelected.value

    Column(
        modifier = Modifier
            .padding(start = 32.dp, end = 32.dp, top = 48.dp, bottom = 16.dp)
    ) {
        LazyColumn(Modifier.weight(1f)) {
            item {
                Text(
                    text = "What type of medication did you take?",
                    style = Typography.h5,
                    color = MaterialTheme.colors.onPrimary
                )
            }
            item { VerticalSpacer(height = 32.dp) }
            items(viewModel.medicationTypes.value) { item ->
                RadioAnswer(
                    selected = answerState == item.id,
                    answerText = item.name,
                ) {
                    viewModel.onAnswerUpdated(item.id)
                }
            }
            item {
                RadioEditEntry(selected = answerState == CUSTOM_ANSWER_ID, onAnswerClicked = viewModel::onCustomAnswerUpdated)
            }
        }
        Button(
            modifier = Modifier.align(Alignment.End),
            onClick = { viewModel.onQuestionnaireComplete() }
        ) {
            Text(text = "Submit Medication")
        }
    }
}