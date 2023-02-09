package com.mecofarid.trending

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.mecofarid.trending.common.ui.resources.TrendingTheme
import com.mecofarid.trending.ui.navigation.MainNavigation

class MainActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TrendingTheme {
                MainNavigation()
            }
        }
    }
}