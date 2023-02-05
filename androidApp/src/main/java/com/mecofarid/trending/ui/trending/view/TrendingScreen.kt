package com.mecofarid.trending.ui.trending.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.mecofarid.trending.android.R
import com.mecofarid.trending.common.ui.preview.SystemUiPreview
import com.mecofarid.trending.common.ui.resources.dimen.Dimens
import com.mecofarid.trending.common.ui.resources.theme.TrendingTheme
import com.mecofarid.trending.features.trending.domain.model.Trending
import com.mecofarid.trending.features.trending.ui.UiState
import com.mecofarid.trending.mocks.anyList
import com.mecofarid.trending.mocks.feature.trending.anyTrending
import com.mecofarid.trending.ui.trending.TrendingViewModel
import com.valentinilk.shimmer.shimmer

@Composable
fun TrendingScreen(viewModel: TrendingViewModel) {
    val state by viewModel.store.uiState.collectAsStateWithLifecycle()
    state.apply {
        when (this) {
            UiState.Loading -> Loading()
            UiState.NoData -> NoData()
            is UiState.Success -> Success(trendingList)
        }
    }
}

@Composable
fun Loading(){
    val placeholderColor = TrendingTheme.colorSchema.viewPlaceholderBg
    LazyColumn(Modifier.shimmer()){
        items(20){
            Spacer(Modifier.height(Dimens.gu_1_5))
            Row(Modifier.height(IntrinsicSize.Max)) {
                Image(
                    ColorPainter(placeholderColor),
                    contentDescription = null,
                    Modifier
                        .size(Dimens.gu_6)
                        .clip(CircleShape)
                )
                Spacer(Modifier.width(Dimens.gu_2))
               Column(
                   Modifier.fillMaxHeight(),
                   Arrangement.SpaceAround
               ) {
                   Spacer(
                       Modifier
                           .background(placeholderColor)
                           .fillMaxWidth()
                           .height(Dimens.gu_1_5)
                   )
                   Spacer(
                       Modifier
                           .background(placeholderColor)
                           .fillMaxWidth()
                           .height(Dimens.gu_1_5)
                   )
               }
            }
        }
    }
}

@Composable
fun Success(trendingList: List<Trending>) {
    LazyColumn(
        contentPadding = PaddingValues(Dimens.gu)
    ){
        itemsIndexed(
            trendingList,
            key = { _, item -> item.trendingId }
        ) { index, item ->
            TrendingView(item)
            if (index < trendingList.lastIndex)
                Divider(
                    thickness = Dimens.gu_0_125,
                    color = TrendingTheme.colorSchema.viewPlaceholderBg
                )
        }
    }
}

@Composable
fun NoData(){
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.no_data))
    val progress by animateLottieCompositionAsState(composition)
    LottieAnimation(
        composition = composition,
        progress = { progress },
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dimens.gu_6)
    )
}

@SystemUiPreview
@Composable
fun PreviewSuccess(){
    TrendingTheme {
        Success(trendingList = anyList(length = 3) { anyTrending() })
    }
}

@SystemUiPreview
@Composable
fun PreviewLoading(){
    TrendingTheme {
        Loading()
    }
}

//@SystemUiPreview
//@Composable
//fun PreviewTrendingScreen(){
//    TrendingTheme {
//        TrendingScreen()
//    }
//}
