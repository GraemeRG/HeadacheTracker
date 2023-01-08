package com.sbnl.headachetracker

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectableGroup
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
            .padding(horizontal = 32.dp, vertical = 48.dp)
    ) {
        Text(text = "When did the headache start?", style = Typography.h5)
        VerticalSpacer(height = 32.dp)
        RadioAnswer(
            selected = answerState.value == 1,
            answerText = "Woke up with it"
        ) { viewModel.onAnswerUpdated(1) }
        RadioAnswer(
            selected = answerState.value == 2,
            answerText = "During the day"
        ) { viewModel.onAnswerUpdated(2) }
        RadioAnswer(
            selected = answerState.value == 3,
            answerText = "In the evening"
        ) { viewModel.onAnswerUpdated(3) }
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
        Text(text = answerText, style = Typography.subtitle1)
    }
}

@Preview
@Composable
fun HeadacheQuestionairePreview() {
    HeadacheQuestionnaire()
}