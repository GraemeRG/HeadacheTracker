package com.sbnl.headachetracker.features.homescreen.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sbnl.headachetracker.R
import com.sbnl.headachetracker.ui.sharedui.TextWithIconAbove
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
            text = stringResource(id = R.string.last_seven_day_title),
            style = Typography.subtitle1,
            color = MaterialTheme.colors.onPrimary
        )
        Row(horizontalArrangement = Arrangement.SpaceAround) {
            TextWithIconAbove(R.drawable.ic_mood_bad, PastelRed, "M")
            TextWithIconAbove(R.drawable.ic_mood_good, PastelGreen, "T")
            TextWithIconAbove(R.drawable.ic_mood_good, PastelGreen, "W")
            TextWithIconAbove(R.drawable.ic_mood_bad, PastelRed, "T")
            TextWithIconAbove(R.drawable.ic_mood_good, PastelGreen, "F")
            TextWithIconAbove(R.drawable.ic_mood_bad, PastelRed, "S")
            TextWithIconAbove(R.drawable.ic_mood_good, PastelGreen, "S")
        }
    }
}