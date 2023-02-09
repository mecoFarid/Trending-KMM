package com.mecofarid.trending.ui.trending.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import com.mecofarid.trending.android.R
import com.mecofarid.trending.common.ext.toNA
import com.mecofarid.trending.common.ui.preview.SystemUiPreview
import com.mecofarid.trending.common.ui.resources.Dimens
import com.mecofarid.trending.common.ui.resources.TrendingTheme
import com.mecofarid.trending.features.trending.domain.model.Trending
import com.mecofarid.trending.mocks.feature.trending.anyTrending

@Composable
fun TrendingView(trending: Trending) {
    var showDetails by rememberSaveable { mutableStateOf(false) }
    ConstraintLayout(
        Modifier
            .clickable {
                showDetails = !showDetails
            }
            .padding(vertical = Dimens.gu)
            .fillMaxWidth()
    ) {
        val (avatar, loginAndName, details) = createRefs()
        AsyncImage(
            model = trending.owner.avatarUrl,
            modifier = Modifier
                .size(Dimens.gu_6)
                .clip(CircleShape)
                .constrainAs(avatar) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                },
            placeholder = ColorPainter(TrendingTheme.colorSchema.viewPlaceholderBg),
            contentDescription = null
        )

        Column(
            Modifier
                .constrainAs(loginAndName) {
                    linkTo(
                        top = avatar.top,
                        bottom = avatar.bottom,
                        start = avatar.end,
                        startMargin = Dimens.gu_2,
                        end = parent.end
                    )
                    width = Dimension.fillToConstraints
                }
        ) {
            Text(text = trending.owner.login, style = MaterialTheme.typography.labelMedium)
            Text(text = trending.name, style = MaterialTheme.typography.labelSmall)
        }

        if (showDetails)
            DetailsView(
                Modifier.constrainAs(details) {
                    top.linkTo(avatar.bottom)
                    start.linkTo(loginAndName.start)
                    end.linkTo(parent.end)
                    width = Dimension.fillToConstraints
                },
                trending
            )
    }
}

@Composable
private fun DetailsView(
    modifier: Modifier = Modifier,
    trending: Trending
) = with(trending){
    Column(modifier.padding(top = Dimens.gu)) {
        Text(
            text = descriptionText.orEmpty(),
            style = MaterialTheme.typography.labelSmall
        )
        Row(Modifier.padding(top = Dimens.gu)){
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

