package com.sbnl.headachetracker.features.homescreen.composables

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sbnl.headachetracker.R
import com.sbnl.headachetracker.features.homescreen.HomeScreenState
import com.sbnl.headachetracker.features.homescreen.HomeScreenViewModel
import com.sbnl.headachetracker.features.homescreen.RecordButtonConfiguration
import com.sbnl.headachetracker.features.homescreen.ReportHeadacheGoneViewModel
import com.sbnl.headachetracker.ui.theme.*
import org.koin.androidx.compose.getViewModel

@Composable
fun HeadacheTrackerHomeScreen(
    homeScreenViewModel: HomeScreenViewModel = getViewModel(),
    reportHeadacheClearedViewModel: ReportHeadacheGoneViewModel = getViewModel(),
    onLaunchToHeadacheQuestionnaire: () -> Unit
) {
    val screenState = homeScreenViewModel.screenState

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.surface
    ) {
        when(val state = screenState.value) {
            is HomeScreenState.Content -> {
                HomeScreenContent(
                    state,
                    onLaunchToHeadacheQuestionnaire,
                    reportHeadacheClearedViewModel
                )
            }
            HomeScreenState.Loading -> {

            }
        }
    }

    homeScreenViewModel.onEnterScreen()
}

@Composable
private fun HomeScreenContent(
    state: HomeScreenState.Content,
    onLaunchToHeadacheQuestionnaire: () -> Unit,
    reportHeadacheClearedViewModel: ReportHeadacheGoneViewModel
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        LastSevenDays()
        when (val config = state.recordButtonConfig) {
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
}

@Composable
fun RecordAHeadacheButton(onClick: () -> Unit) {
    Button(
        modifier = Modifier.size(128.dp),
        onClick = { onClick() },
        border = BorderStroke(width = 1.dp, color = Color.White),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = SoftRed,
            contentColor = PastelWhite
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(painter = painterResource(id = R.drawable.ic_skull), contentDescription = null)
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = "Record a headache",
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun RecordHeadacheGoneButton(onClick: () -> Unit) {
    Button(
        modifier = Modifier.size(128.dp),
        onClick = { onClick() },
        border = BorderStroke(width = 1.dp, color = Color.White),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = DesaturatedDarkBlue,
            contentColor = PastelWhite
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(painter = painterResource(id = R.drawable.ic_bolt), contentDescription = null)
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = "Record headache gone",
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview
@Composable
fun HomeScreenComposablesPreview() {
    HeadacheTrackerTheme {
        HeadacheTrackerHomeScreen() {

        }
    }
}