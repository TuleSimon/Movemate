package com.simon.movemate.ui.navigation.mainNav


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.movemate.shared.LocalSharedTransitionScope
import com.movemate.shared.routes.CalculateRoute
import com.movemate.shared.routes.HomeRoute
import com.movemate.shared.routes.ProfileRoute
import com.movemate.shared.routes.ShipmentRoute
import com.simon.movemate.ui.navigation.bottomNav.HomeBottomNavigation

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun HomeBottomNavGraph(
    navController: NavHostController
) {
    SharedTransitionLayout(
        modifier = Modifier.background(Color.White),
    ) {
        CompositionLocalProvider(
            LocalSharedTransitionScope provides this,
        ) {
            Scaffold(
                bottomBar = {
                    AnimatedVisibility(
                        visible = true,
                        enter = slideIn(tween(10)) { IntOffset.Zero },
                        exit = slideOut(
                            tween(10),
                        ) { IntOffset.Zero },
                    ) {
                        HomeBottomNavigation(navController)
                    }
                },
            ) {
                Box(
                    modifier = Modifier
                        .padding(bottom = it.calculateBottomPadding())
                        .fillMaxSize(),
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = HomeRoute,
                    ) {


                        composable<HomeRoute>() {
                            Box() {
                                Text("")
                            }
                        }

                        composable<CalculateRoute>() {
                            Box() {
                                Text("")
                            }
                        }
                        composable<ShipmentRoute>() {
                            Box() {
                                Text("")
                            }
                        }
                        composable<ProfileRoute>() {
                            Box() {
                                Text("")
                            }
                        }


                    }
                }
            }
        }
    }
}