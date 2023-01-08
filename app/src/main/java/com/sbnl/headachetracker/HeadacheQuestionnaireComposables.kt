package com.sbnl.headachetracker

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sbnl.headachetracker.ui.theme.Typography

@Composable
fun HeadacheQuestionnaire(viewModel: HeadacheQuestionnaireViewModel = HeadacheQuestionnaireViewModel()) {
    WhenDidTheHeadacheStartQuestion(viewModel)
}

@Composable
fun WhenDidTheHeadacheStartQuestion(viewModel: HeadacheQuestionnaireViewModel) {

    val answerState = viewModel.answerToQuestion

    Column(
        modifier = Modifier
            .padding(start = 32.dp, end = 32.dp, top = 48.dp, bottom = 16.dp)
    ) {
        LazyColumn(Modifier.weight(1f)) {
            item { Text(text = "When did the headache start?", style = Typography.h5, color = MaterialTheme.colors.onPrimary) }
            item { VerticalSpacer(height = 32.dp) }
            item {
                RadioAnswer(
                    selected = answerState.value == 1,
                    answerText = "Woke up with it"
                ) { viewModel.onAnswerUpdated(1) }
            }
            item {
                RadioAnswer(
                    selected = answerState.value == 2,
                    answerText = "During the day"
                ) { viewModel.onAnswerUpdated(2) }
            }
            item {
                RadioAnswer(
                    selected = answerState.value == 3,
                    answerText = "In the evening"
                ) { viewModel.onAnswerUpdated(3) }
            }
        }
        Button(
            modifier = Modifier.align(Alignment.End),
            onClick = { viewModel.onQuestionnaireComplete() }
        ) {
            Text(text = "Submit Headache")
        }
    }
}

@Composable
private fun VerticalSpacer(height: Dp) {
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(height)
    )
}

@Composable
fun RadioAnswer(selected: Boolean, answerText: String, onAnswerClicked: () -> Unit) {
    Row(
        modifier = Modifier
            .clickable { onAnswerClicked() }
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(selected = selected, onClick = onAnswerClicked)
        Text(text = answerText, style = Typography.subtitle1, color = MaterialTheme.colors.onPrimary)
    }
}

@Preview
@Composable
fun HeadacheQuestionairePreview() {
    HeadacheQuestionnaire()
}