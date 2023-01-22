package com.sbnl.headachetracker.features.headachequestionnaire.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sbnl.headachetracker.R
import com.sbnl.headachetracker.features.headachequestionnaire.HeadacheQuestionnaireViewModel
import com.sbnl.headachetracker.ui.sharedui.QuestionTitleText
import com.sbnl.headachetracker.ui.sharedui.TextWithIconAbove
import com.sbnl.headachetracker.ui.sharedui.VerticalSpacer
import com.sbnl.headachetracker.ui.theme.*

@Composable
fun InitialPainLevelQuestion(viewModel: HeadacheQuestionnaireViewModel) {
    Column {
        QuestionTitleText(stringResource(id = R.string.hq_question_pain_level))
        VerticalSpacer(height = 32.dp)
        PainLevelOptions(viewModel::onInitialPainLevelUpdated)
    }
}

@Composable
private fun PainLevelOptions(onOptionClicked: (Int) -> Unit) {
    LazyRow {
        items(count = 10) { index ->
            val painLevel = index + 1
            IconButton(onClick = { onOptionClicked(painLevel) }) {
                TextWithIconAbove(
                    modifier = Modifier.requiredWidth(48.dp),
                    icon = R.drawable.ic_mood_good,
                    iconColor = iconColorForPainLevel(painLevel),
                    text = "$painLevel"
                )
            }
        }
    }
}

fun iconColorForPainLevel(painLevel: Int): Color =
    when {
        painLevel <= 3 -> {
            MildGreen
        }
        painLevel == 4 -> {
            ModerateYellow
        }
        painLevel <= 6 -> {
            SevereOrange
        }
        painLevel <= 9 -> {
            VerySevereOrange
        }
        else -> {
            WorstPainRed
        }
    }

@Preview
@Composable
fun PainLevelPreview() {
    PainLevelOptions { }
}