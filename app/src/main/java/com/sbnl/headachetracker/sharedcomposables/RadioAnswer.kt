package com.sbnl.headachetracker.sharedcomposables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.sbnl.headachetracker.ui.theme.Typography

@Composable
fun RadioAnswer(selected: Boolean, answerText: String, onAnswerClicked: () -> Unit) {
    Row(
        modifier = Modifier
            .clickable { onAnswerClicked() }
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(selected = selected, onClick = onAnswerClicked)
        Text(
            text = answerText,
            style = Typography.subtitle1,
            color = MaterialTheme.colors.onPrimary
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RadioEditEntry(selected: Boolean, onAnswerClicked: (String) -> Unit) {
    var text by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(selected = selected, onClick = { onAnswerClicked.invoke(text) })
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = text,
            onValueChange = {
                text = it
                onAnswerClicked(it)
            }
        )
    }
}

@Preview
@Composable
fun RadioFields() {
    Column {
        RadioAnswer(selected = true, answerText = "Selected Option") { }
        RadioAnswer(selected = false, answerText = "Deselected Option") { }
        RadioEditEntry(selected = false) { }
    }
}