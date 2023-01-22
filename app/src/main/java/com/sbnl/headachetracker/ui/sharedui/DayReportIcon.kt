package com.sbnl.headachetracker.ui.sharedui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource

@Composable
fun TextWithIconAbove(
    @DrawableRes icon: Int,
    iconColor: Color,
    text: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            tint = iconColor,
            painter = painterResource(id = icon),
            contentDescription = null
        )
        Text(text = text, color = MaterialTheme.colors.onPrimary)
    }
}