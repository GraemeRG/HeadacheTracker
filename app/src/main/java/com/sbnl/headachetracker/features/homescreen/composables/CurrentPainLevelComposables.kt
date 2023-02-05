package com.sbnl.headachetracker.features.homescreen.composables

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sbnl.headachetracker.R
import com.sbnl.headachetracker.features.homescreen.PainLevelDisplayObject
import com.sbnl.headachetracker.ui.sharedui.iconColorForPainLevel
import com.sbnl.headachetracker.ui.theme.HeadacheTrackerTheme
import com.sbnl.headachetracker.ui.theme.Typography

@Composable
fun CurrentMoodIndicator(painLevel: PainLevelDisplayObject, onUpdatePainLevel: () -> Unit) {
    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth(), elevation = 4.dp
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                modifier = Modifier
                    .padding(16.dp)
                    .height(56.dp)
                    .width(56.dp),
                painter = painterResource(id = R.drawable.ic_mood_good),
                contentDescription = null,
                tint = iconColorForPainLevel(painLevel.numericLevel)
            )
            Column(modifier = Modifier.weight(1f)) {
                Text(text = "Your Current Mood", style = Typography.subtitle1)

                painLevel
                    .recorded
                    ?.apply {
                        Text(
                            text = "Last recorded: $this",
                            style = Typography.caption
                        )
                    }
            }
            IconButton(onClick = onUpdatePainLevel) {
                Icon(painter = painterResource(id = R.drawable.ic_add), contentDescription = null)
            }
        }
    }
}

@Preview
@Composable
fun PainLevelComposablePreview() {
    HeadacheTrackerTheme {
        CurrentMoodIndicator(PainLevelDisplayObject(numericLevel = 1)) {

        }
    }
}