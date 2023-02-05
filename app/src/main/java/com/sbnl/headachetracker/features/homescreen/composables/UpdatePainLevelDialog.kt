package com.sbnl.headachetracker.features.homescreen.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sbnl.headachetracker.ui.sharedui.PainLevelOptions
import com.sbnl.headachetracker.ui.sharedui.VerticalSpacer
import com.sbnl.headachetracker.ui.theme.HeadacheTrackerTheme
import com.sbnl.headachetracker.ui.theme.Typography

@Composable
fun UpdatePainLevelDialog(onDismissRequest: () -> Unit, onConfirmClicked: (Int) -> Unit) {
    val selectedLevel = remember { mutableStateOf(-1) }

    AlertDialog(
        onDismissRequest = { onDismissRequest() },
        title = { Text(text = "Update your mood", style = Typography.h5) },
        text = {
            Column {
                Text(
                    text = "Tell us how you're feeling by updating your mood below:",
                    style = Typography.body1
                )
                VerticalSpacer(height = 8.dp)
                PainLevelOptions(onOptionClicked = { selectedLevel.value = it })
            }
        },
        confirmButton = {
            Button(onClick = { onConfirmClicked(selectedLevel.value) }) {
                Text(text = "Confirm")
            }
        }
    )
}

@Preview
@Composable
fun UpdatePainLevelDialogPreview() {
    HeadacheTrackerTheme {
        UpdatePainLevelDialog(
            onDismissRequest = {},
            onConfirmClicked = {}
        )
    }
}