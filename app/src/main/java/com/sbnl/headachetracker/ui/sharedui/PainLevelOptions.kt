package com.sbnl.headachetracker.ui.sharedui

import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.sbnl.headachetracker.R
import com.sbnl.headachetracker.ui.theme.*

@Composable
fun PainLevelOptions(onOptionClicked: (Int) -> Unit) {
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