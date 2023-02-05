package com.mecofarid.trending.ui.trending.view

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.mecofarid.trending.android.R
import com.mecofarid.trending.common.ui.preview.SystemUiPreview
import com.mecofarid.trending.common.ui.resources.dimen.Dimens
import com.mecofarid.trending.common.ui.resources.theme.TrendingTheme

@Composable
fun ImageText(
    @DrawableRes image: Int,
    imageColor: Color,
    text: String,
    modifier: Modifier = Modifier
) {
    Row(modifier, verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = painterResource(id = image),
            modifier = Modifier.size(Dimens.gu_1_5),
            colorFilter = ColorFilter.tint(imageColor),
            contentDescription = null
        )
        Spacer(modifier = Modifier.padding(end = Dimens.gu_0_75))
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall
        )
    }
}

@SystemUiPreview
@Composable
fun PreviewImageText(){
    TrendingTheme {
        ImageText(
            image = R.drawable.ic_star,
            imageColor = TrendingTheme.colorSchema.trendingItemStarColor,
            text = "Some text"
        )
    }
}