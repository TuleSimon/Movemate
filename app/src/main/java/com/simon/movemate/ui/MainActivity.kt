package com.simon.movemate.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.movemate.core.theme.base.MovemateTheme
import com.simon.movemate.ui.navigation.mainNav.HomeBottomNavGraph
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    var keepSplash = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val splash = installSplashScreen()

        splash.setKeepOnScreenCondition {
            keepSplash
        }
        lifecycleScope.launch {
            delay(1000)
            keepSplash = false
        }
        enableEdgeToEdge()
        setContent {
            MovemateTheme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { _ ->

                    HomeBottomNavGraph(navController)

                }
            }
        }
    }
}

