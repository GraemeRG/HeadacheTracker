package com.sbnl.headachetracker.features.headachequestionnaire.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sbnl.headachetracker.R
import com.sbnl.headachetracker.features.headachequestionnaire.HeadacheQuestionnaireViewModel
import com.sbnl.headachetracker.ui.sharedui.PainLevelOptions
import com.sbnl.headachetracker.ui.sharedui.QuestionTitleText
import com.sbnl.headachetracker.ui.sharedui.VerticalSpacer

@Composable
fun InitialPainLevelQuestion(viewModel: HeadacheQuestionnaireViewModel) {
    Column {
        QuestionTitleText(stringResource(id = R.string.hq_question_pain_level))
        VerticalSpacer(height = 32.dp)
        PainLevelOptions(viewModel::onInitialPainLevelUpdated)
    }
}

@Preview
@Composable
fun PainLevelPreview() {
    PainLevelOptions { }
}