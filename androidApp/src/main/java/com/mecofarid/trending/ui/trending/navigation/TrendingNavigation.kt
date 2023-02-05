package com.mecofarid.trending.ui.trending.navigation

import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.mecofarid.trending.ui.trending.TrendingViewModel
import com.mecofarid.trending.ui.trending.view.TrendingScreen

private const val NAV_TRENDING = "trending"
const val NAV_TRENDING_ROUTE = "trending_route"

fun NavGraphBuilder.trendingGraph(){
    navigation(NAV_TRENDING, NAV_TRENDING_ROUTE){
        composable(NAV_TRENDING){
            TrendingScreen(viewModel(factory = TrendingViewModel.Factory))
        }
    }
}