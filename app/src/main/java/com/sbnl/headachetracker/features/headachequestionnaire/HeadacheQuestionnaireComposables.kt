package com.sbnl.headachetracker.features.headachequestionnaire

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sbnl.headachetracker.R
import com.sbnl.headachetracker.database.headache.HeadacheStartPeriod.*
import com.sbnl.headachetracker.sharedcomposables.RadioAnswer
import com.sbnl.headachetracker.sharedcomposables.VerticalSpacer
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
                    text = stringResource(id = R.string.hq_question_start_period),
                    style = Typography.h5,
                    color = MaterialTheme.colors.onPrimary
                )
            }
            item { VerticalSpacer(height = 32.dp) }
            item {
                RadioAnswer(
                    selected = answerState.value == WOKE_UP,
                    answerText = stringResource(id = R.string.hq_option_woke_up)
                ) { viewModel.onAnswerUpdated(WOKE_UP) }
            }
            item {
                RadioAnswer(
                    selected = answerState.value == DURING_DAY,
                    answerText = stringResource(id = R.string.hq_option_day)
                ) { viewModel.onAnswerUpdated(DURING_DAY) }
            }
            item {
                RadioAnswer(
                    selected = answerState.value == DURING_EVENING,
                    answerText = stringResource(id = R.string.hq_option_evening)
                ) { viewModel.onAnswerUpdated(DURING_EVENING) }
            }
        }
        Button(
            modifier = Modifier.align(Alignment.End),
            onClick = { viewModel.onQuestionnaireComplete() }
        ) {
            Text(text = stringResource(id = R.string.hq_submit))
        }
    }
}

@Preview
@Composable
fun HeadacheQuestionnairePreview() {
    HeadacheQuestionnaire({})
}