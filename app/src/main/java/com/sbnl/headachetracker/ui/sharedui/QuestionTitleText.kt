package com.sbnl.headachetracker.ui.sharedui

import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.sbnl.headachetracker.ui.theme.Typography

@Composable
fun QuestionTitleText(text: String) {
    Text(
        text = text,
        style = Typography.h5,
        color = MaterialTheme.colors.onPrimary
    )
}