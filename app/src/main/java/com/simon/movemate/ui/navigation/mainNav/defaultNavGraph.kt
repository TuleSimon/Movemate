package com.simon.movemate.ui.navigation.mainNav


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseInCubic
import androidx.compose.animation.core.EaseInElastic
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.movemate.home.presentation.screen.HomeScreen
import com.movemate.shared.LocalSharedTransitionScope
import com.movemate.shared.getSharedViewModel
import com.movemate.shared.routes.CalculateRoute
import com.movemate.shared.routes.HomeRoute
import com.movemate.shared.routes.ProfileRoute
import com.movemate.shared.routes.ShipmentRoute
import com.movemate.shared.viewmodel.MoveMateSharedViewModel
import com.movemate.shared.viewmodel.MovemateGlobalAppState
import com.simon.movemate.ui.navigation.bottomNav.HomeBottomNavigation

const val animationDuration = 300

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun HomeBottomNavGraph(
    navController: NavHostController,
    moveMateSharedViewModel: MoveMateSharedViewModel = getSharedViewModel()
) {
    SharedTransitionLayout(
        modifier = Modifier.background(Color.White),
    ) {
        val sharedState =
            moveMateSharedViewModel.moveMateAppState
                .collectAsStateWithLifecycle(
                    MovemateGlobalAppState()
                ).value

        CompositionLocalProvider(
            LocalSharedTransitionScope provides this,
        ) {
            Scaffold(
                bottomBar = {
                    AnimatedVisibility(
                        visible = sharedState.showBottomNav,
                        enter = slideInVertically(
                            tween(
                                animationDuration,
                                easing = EaseInCubic
                            )
                        ) { it / 2 },
                        exit = slideOutVertically(
                            tween(
                                animationDuration,
                                easing = EaseIn
                            )
                        ) { it / 2 },
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
                            HomeScreen()
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