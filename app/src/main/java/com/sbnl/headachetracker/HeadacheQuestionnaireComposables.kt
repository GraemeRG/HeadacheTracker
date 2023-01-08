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
import com.sbnl.headachetracker.database.HeadacheStartPeriod.*
import com.sbnl.headachetracker.ui.theme.Typography
import org.koin.androidx.compose.getViewModel

@Composable
fun HeadacheQuestionnaire(returnToHomeScreen: () -> Unit, viewModel: HeadacheQuestionnaireViewModel = getViewModel()) {
    val shouldReturnToHomeScreen = viewModel.returnToHomeScreen.value
    if(shouldReturnToHomeScreen >= 0) {
        returnToHomeScreen()
    }

    WhenDidTheHeadacheStartQuestion(viewModel)
}

@Composable
fun WhenDidTheHeadacheStartQuestion(viewModel: HeadacheQuestionnaireViewModel) {

    val answerState = viewModel.headacheStartPeriod

    Column(
        modifier = Modifier
            .padding(start = 32.dp, end = 32.dp, top = 48.dp, bottom = 16.dp)
    ) {
        LazyColumn(Modifier.weight(1f)) {
            item {
                Text(
                    text = "When did the headache start?",
                    style = Typography.h5,
                    color = MaterialTheme.colors.onPrimary
                )
            }
            item { VerticalSpacer(height = 32.dp) }
            item {
                RadioAnswer(
                    selected = answerState.value == WOKE_UP,
                    answerText = "Woke up with it"
                ) { viewModel.onAnswerUpdated(WOKE_UP) }
            }
            item {
                RadioAnswer(
                    selected = answerState.value == DURING_DAY,
                    answerText = "During the day"
                ) { viewModel.onAnswerUpdated(DURING_DAY) }
            }
            item {
                RadioAnswer(
                    selected = answerState.value == DURING_EVENING,
                    answerText = "In the evening"
                ) { viewModel.onAnswerUpdated(DURING_EVENING) }
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
        Text(
            text = answerText,
            style = Typography.subtitle1,
            color = MaterialTheme.colors.onPrimary
        )
    }
}

@Preview
@Composable
fun HeadacheQuestionnairePreview() {
    HeadacheQuestionnaire({})
}