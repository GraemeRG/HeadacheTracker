package com.sbnl.headachetracker.features.homescreen.composables

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.sbnl.headachetracker.R
import com.sbnl.headachetracker.features.homescreen.HomeScreenState.*
import com.sbnl.headachetracker.features.homescreen.HomeScreenViewModel
import com.sbnl.headachetracker.features.homescreen.RecordButtonConfiguration
import com.sbnl.headachetracker.features.homescreen.ReportHeadacheGoneViewModel
import com.sbnl.headachetracker.ui.theme.*
import org.koin.androidx.compose.getViewModel

@Composable
fun HeadacheTrackerHomeScreen(
    homeScreenViewModel: HomeScreenViewModel = getViewModel(),
    reportHeadacheClearedViewModel: ReportHeadacheGoneViewModel = getViewModel(),
    onLaunchToHeadacheQuestionnaire: () -> Unit,
    onLaunchToMedicationTakenQuestionnaire: () -> Unit
) {
    val screenState = homeScreenViewModel.screenState

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.surface
    ) {
        when (val state = screenState.value) {
            is Content -> {
                HomeScreenContent(
                    state,
                    onLaunchToHeadacheQuestionnaire,
                    onLaunchToMedicationTakenQuestionnaire,
                    reportHeadacheClearedViewModel
                )
            }
            Loading -> {

            }
        }
    }

    homeScreenViewModel.onEnterScreen()
}

@Composable
private fun HomeScreenContent(
    state: Content,
    onLaunchToHeadacheQuestionnaire: () -> Unit,
    onLaunchToMedicationTakenQuestionnaire: () -> Unit,
    reportHeadacheClearedViewModel: ReportHeadacheGoneViewModel
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        LastSevenDays()
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            RecordMedicationButton {
                onLaunchToMedicationTakenQuestionnaire()
            }
            MainButton(
                state.recordButtonConfig,
                onLaunchToHeadacheQuestionnaire,
                reportHeadacheClearedViewModel
            )
            RecordMedicationButton {
                onLaunchToMedicationTakenQuestionnaire()
            }
        }
        CurrentMoodIndicator(state.currentPainLevel)
    }
}

@Composable
private fun MainButton(
    config: RecordButtonConfiguration,
    onLaunchToHeadacheQuestionnaire: () -> Unit,
    reportHeadacheClearedViewModel: ReportHeadacheGoneViewModel
) {
    when (config) {
        is RecordButtonConfiguration.ReportNew -> {
            RecordAHeadacheButton {
                onLaunchToHeadacheQuestionnaire()
            }
        }
        is RecordButtonConfiguration.ReportFinished -> {
            RecordHeadacheGoneButton {
                reportHeadacheClearedViewModel.reportHeadacheCleared(config.headacheId)
            }
        }
    }
}

@Composable
fun RecordAHeadacheButton(onClick: () -> Unit) {
    NewRecordButton(
        size = 128.dp,
        onClick = { onClick() },
        backgroundColor = SoftRed,
        text = stringResource(id = R.string.button_headache),
        iconRes = R.drawable.ic_skull
    )
}

@Composable
fun RecordHeadacheGoneButton(onClick: () -> Unit) {
    NewRecordButton(
        size = 128.dp,
        onClick = { onClick() },
        backgroundColor = DesaturatedDarkBlue,
        text = stringResource(id = R.string.button_headache_gone),
        iconRes = R.drawable.ic_bolt
    )
}

@Composable
fun RecordMedicationButton(onClick: () -> Unit) {
    NewRecordButton(
        size = 120.dp,
        onClick = { onClick() },
        backgroundColor = GrayishBlue,
        text = stringResource(id = R.string.button_medication),
        iconRes = R.drawable.ic_medication
    )
}

@Composable
fun NewRecordButton(
    size: Dp,
    onClick: () -> Unit,
    backgroundColor: Color,
    text: String,
    @DrawableRes iconRes: Int
) {
    Button(
        modifier = Modifier.size(size),
        onClick = { onClick() },
        border = BorderStroke(width = 1.dp, color = Color.White),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor,
            contentColor = PastelWhite
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(painter = painterResource(id = iconRes), contentDescription = null)
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = text,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview
@Composable
fun HomeScreenComposablesPreview() {
    HeadacheTrackerTheme {
        Row(verticalAlignment = Alignment.CenterVertically) {
            RecordMedicationButton { }
            RecordAHeadacheButton { }
            RecordHeadacheGoneButton { }
        }
    }
}