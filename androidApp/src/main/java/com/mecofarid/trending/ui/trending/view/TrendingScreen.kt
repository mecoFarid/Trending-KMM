package com.mecofarid.trending.ui.trending.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.airbnb.lottie.compose.*
import com.mecofarid.trending.android.R
import com.mecofarid.trending.common.ui.libs.shimmer.customShimmer
import com.mecofarid.trending.common.ui.preview.SystemUiPreview
import com.mecofarid.trending.common.ui.resources.dimen.Dimens
import com.mecofarid.trending.common.ui.resources.theme.TrendingTheme
import com.mecofarid.trending.features.trending.domain.model.Trending
import com.mecofarid.trending.features.trending.ui.UiState
import com.mecofarid.trending.mocks.anyList
import com.mecofarid.trending.mocks.feature.trending.anyTrending
import com.mecofarid.trending.ui.trending.TrendingViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrendingScreen(viewModel: TrendingViewModel) {
    val state by viewModel.store.uiState.collectAsStateWithLifecycle()
    Scaffold(
        topBar = {
                 CenterAlignedTopAppBar(
                     title = {
                         Text(
                             text = stringResource(id = R.string.app_name),
                             modifier = Modifier
                                 .fillMaxSize()
                                 .wrapContentSize(Alignment.Center)
                         )
                     }
                 )
        },
        floatingActionButton = {
            if (state !is UiState.Loading)
                FloatingActionButton(
                    modifier = Modifier.size(Dimens.gu_6),
                    onClick = { viewModel.store.refresh() }
                ) {
                    Icon(
                        painter = rememberVectorPainter(image = Icons.Filled.Refresh),
                        contentDescription = null
                    )
                }
        },
        content = {
            Box(modifier = Modifier
                .padding(it)
                .padding(horizontal = Dimens.gu_2)
            ){
                state.apply {
                    when (this) {
                        UiState.Loading -> Loading()
                        UiState.NoData -> NoData()
                        is UiState.Success -> Success(trendingList)
                    }
                }
            }
        }
    )
}

@Composable
fun Loading() {
    val placeholderColor = TrendingTheme.colorSchema.viewPlaceholderBg
    LazyColumn(Modifier.customShimmer()) {
        items(20) {
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
        contentPadding = PaddingValues(top = Dimens.gu, bottom = Dimens.gu_7)
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
fun NoData() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.no_data))
    val progress by animateLottieCompositionAsState(
        composition,
        iterations = LottieConstants.IterateForever
    )
    LottieAnimation(
        composition = composition,
        progress = { progress },
        modifier = Modifier
            .fillMaxSize()
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
