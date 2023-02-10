package com.mecofarid.trending.common.ui.resources

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle

@Composable
fun typography() = Typography(
    labelSmall = TextStyle(
        color = TrendingTheme.colorSchema.secondaryTextColor,
        fontSize = MaterialTheme.typography.labelSmall.fontSize
    )
)