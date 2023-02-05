package com.mecofarid.trending.ui.trending.view

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.mecofarid.trending.android.R
import com.mecofarid.trending.common.ext.toNA
import com.mecofarid.trending.common.ui.preview.SystemUiPreview
import com.mecofarid.trending.common.ui.resources.dimen.Dimens
import com.mecofarid.trending.common.ui.resources.theme.TrendingTheme
import com.mecofarid.trending.features.trending.domain.model.Trending
import com.mecofarid.trending.mocks.feature.trending.anyTrending
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

@Composable
fun TrendingView(trending: Trending) {
    var showDetails by rememberSaveable { mutableStateOf(false) }
    Column(
        Modifier
            .clickable {
                showDetails = !showDetails
            }
            .padding(vertical = Dimens.gu)
            .fillMaxWidth()
    ) {
        Row(Modifier.height(IntrinsicSize.Max)) {
            AsyncImage(
                model = trending.owner.avatarUrl,
                modifier = Modifier
                    .size(Dimens.gu_6)
                    .clip(CircleShape),
                placeholder = ColorPainter(TrendingTheme.colorSchema.viewPlaceholderBg),
                contentDescription = null
            )
            Column(
                Modifier.padding(start = Dimens.gu_2).fillMaxHeight(),
                Arrangement.SpaceEvenly,
            ) {
                Text(text = trending.owner.login, style = MaterialTheme.typography.labelSmall)
                Text(text = trending.name, style = MaterialTheme.typography.labelSmall)
            }
        }
        if (showDetails)
            DetailsView(trending)
    }
}

@Composable
private fun DetailsView(
    trending: Trending
) = with(trending){
    Column(Modifier.padding(top = Dimens.gu)) {
        Text(
            text = descriptionText.orEmpty(),
            style = MaterialTheme.typography.labelSmall
        )
        Row{
            ImageText(
                image = R.drawable.ic_star,
                imageColor = TrendingTheme.colorSchema.trendingItemStarColor,
                text = stargazersCount.toString(),
                modifier = Modifier.weight(1f)
            )
            ImageText(
                image = R.drawable.ic_circle,
                imageColor = TrendingTheme.colorSchema.trendingItemLanguageIndicatorColor,
                text = language.toNA(),
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@SystemUiPreview
@Composable
fun PreviewTrendingView(){
    TrendingTheme {
        TrendingView(anyTrending())
    }
}

