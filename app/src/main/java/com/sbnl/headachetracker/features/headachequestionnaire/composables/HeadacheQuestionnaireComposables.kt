package com.sbnl.headachetracker.features.headachequestionnaire.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sbnl.headachetracker.R
import com.sbnl.headachetracker.features.headachequestionnaire.HeadacheQuestionnaireViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun HeadacheQuestionnaire(returnToHomeScreen: () -> Unit, viewModel: HeadacheQuestionnaireViewModel = getViewModel()) {
    val shouldReturnToHomeScreen = viewModel.returnToHomeScreen.value
    if(shouldReturnToHomeScreen >= 0) {
        returnToHomeScreen()
    }

    Column(
        modifier = Modifier
            .padding(start = 32.dp, end = 32.dp, top = 48.dp, bottom = 16.dp)
    ) {
        LazyColumn(Modifier.weight(1f)) {
            item { WhenDidTheHeadacheStartQuestion(viewModel = viewModel) }
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