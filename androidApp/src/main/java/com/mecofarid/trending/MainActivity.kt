package com.mecofarid.trending

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.navigation.compose.NavHost
import com.mecofarid.trending.common.ui.resources.theme.TrendingTheme
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