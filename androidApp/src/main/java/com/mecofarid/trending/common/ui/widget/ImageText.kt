package com.mecofarid.trending.common.ui.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.mecofarid.trending.android.R
import com.mecofarid.trending.common.ui.resources.theme.TrendingTheme

@Composable
fun ImageText(
    image: Int,
    imageColor: Color,
    text: String
) {
    Row {
        Image(
            painter = painterResource(id = image),
            colorFilter = ColorFilter.tint(imageColor),
            contentDescription = null
        )
        Text(text = text)
    }
}

@Preview
@Composable
fun ImageText_Preview(){
    ImageText(
        image = R.drawable.ic_launcher_foreground,
        imageColor = TrendingTheme.colorSchema.trendingItemStarColor,
        text = "Some text"
    )
}