package com.example.meteortracker

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.meteortracker.presentation.common.components.navigation.NavGraph
import com.example.meteortracker.ui.theme.MeteorTrackerTheme
import com.example.meteortracker.util.LocaleManager
import com.example.meteortracker.util.NetworkUtil
import com.example.meteortracker.util.ThemeManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(LocaleManager.update(base))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpApp()
    }

    private fun setUpApp() {
        if (!NetworkUtil.isInternetAvailable(this)) {
            NetworkUtil.showNoNetworkDialog(this)
        } else {
            setUpUI()
        }
    }

    private fun setUpUI() {
        val isDarkTheme = ThemeManager.getTheme(this)
        setContent {
            MeteorTrackerTheme(darkTheme = isDarkTheme) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavGraph()
                }
            }
        }
    }
}