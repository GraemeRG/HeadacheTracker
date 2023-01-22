package com.sbnl.headachetracker.features.headachequestionnaire.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sbnl.headachetracker.R
import com.sbnl.headachetracker.database.headache.HeadacheStartPeriod
import com.sbnl.headachetracker.features.headachequestionnaire.HeadacheQuestionnaireViewModel
import com.sbnl.headachetracker.ui.sharedui.QuestionTitleText
import com.sbnl.headachetracker.ui.sharedui.RadioAnswer
import com.sbnl.headachetracker.ui.sharedui.VerticalSpacer

@Composable
fun WhenDidTheHeadacheStartQuestion(viewModel: HeadacheQuestionnaireViewModel) {
    val answerState = viewModel.headacheStartPeriod

    Column {
        QuestionTitleText(stringResource(id = R.string.hq_question_start_period))
        VerticalSpacer(height = 32.dp)
        RadioAnswer(
            selected = answerState.value == HeadacheStartPeriod.WOKE_UP,
            answerText = stringResource(id = R.string.hq_option_woke_up)
        ) { viewModel.onStartPeriodUpdated(HeadacheStartPeriod.WOKE_UP) }
        RadioAnswer(
            selected = answerState.value == HeadacheStartPeriod.DURING_DAY,
            answerText = stringResource(id = R.string.hq_option_day)
        ) { viewModel.onStartPeriodUpdated(HeadacheStartPeriod.DURING_DAY) }
        RadioAnswer(
            selected = answerState.value == HeadacheStartPeriod.DURING_EVENING,
            answerText = stringResource(id = R.string.hq_option_evening)
        ) { viewModel.onStartPeriodUpdated(HeadacheStartPeriod.DURING_EVENING) }
    }
}

