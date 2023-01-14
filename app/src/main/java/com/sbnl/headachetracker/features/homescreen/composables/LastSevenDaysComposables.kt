package com.sbnl.headachetracker.features.homescreen.composables

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.sbnl.headachetracker.R
import com.sbnl.headachetracker.ui.theme.PastelGreen
import com.sbnl.headachetracker.ui.theme.PastelRed
import com.sbnl.headachetracker.ui.theme.Typography

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