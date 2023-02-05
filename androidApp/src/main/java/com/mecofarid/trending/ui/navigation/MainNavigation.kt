package com.mecofarid.trending.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.mecofarid.trending.ui.trending.navigation.NAV_TRENDING_ROUTE
import com.mecofarid.trending.ui.trending.navigation.trendingGraph

@Composable
fun MainNavigation() {
    NavHost(navController = rememberNavController(), startDestination = NAV_TRENDING_ROUTE){
        trendingGraph()
    }
}