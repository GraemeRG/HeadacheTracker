package com.sbnl.headachetracker

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import com.sbnl.headachetracker.ui.theme.*

@Composable
fun HeadacheTrackerHomeScreen(onLaunchToHeadacheQuestionnaire: () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.surface
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            LastSevenDays()
            RecordAHeadacheButton {
                onLaunchToHeadacheQuestionnaire()
            }
        }
    }
}

@Composable
fun LastSevenDays() {
    Column(
        modifier = Modifier
            .padding(start = 32.dp, end = 32.dp, top = 48.dp, bottom = 24.dp)
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colors.background,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(horizontal = 16.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(bottom = 8.dp),
            text = "Your Last Seven Days",
            style = Typography.subtitle1,
            color = MaterialTheme.colors.onPrimary
        )
        Row(horizontalArrangement = Arrangement.SpaceAround) {
            DayReportIcon(R.drawable.ic_mood_bad, PastelRed, "M")
            DayReportIcon(R.drawable.ic_mood_good, PastelGreen, "T")
            DayReportIcon(R.drawable.ic_mood_good, PastelGreen, "W")
            DayReportIcon(R.drawable.ic_mood_bad, PastelRed, "T")
            DayReportIcon(R.drawable.ic_mood_good, PastelGreen, "F")
            DayReportIcon(R.drawable.ic_mood_bad, PastelRed, "S")
            DayReportIcon(R.drawable.ic_mood_good, PastelGreen, "S")
        }
    }
}

@Composable
private fun DayReportIcon(@DrawableRes icon: Int, iconColor: Color, dayText: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(
            tint = iconColor,
            painter = painterResource(id = icon),
            contentDescription = null
        )
        Text(text = dayText, color = MaterialTheme.colors.onPrimary)
    }
}

@Composable
fun RecordAHeadacheButton(onClick: () -> Unit) {
    Button(
        modifier = Modifier.size(128.dp),
        onClick = { onClick() },
        border = BorderStroke(width = 1.dp, color = Color.White),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = PastelRed,
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

@Preview
@Composable
fun HomeScreenComposablesPreview() {
    HeadacheTrackerTheme {
        HeadacheTrackerHomeScreen {

        }
    }
}