package com.simon.movemate.ui.navigation.mainNav


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseInCubic
import androidx.compose.animation.core.EaseInElastic
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
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
import com.movemate.calculate.presentation.screen.CalculateScreen
import com.movemate.calculate.presentation.screen.CalculateSuccessContent
import com.movemate.home.presentation.screen.HomeScreen
import com.movemate.shared.LocalSharedTransitionScope
import com.movemate.shared.getSharedViewModel
import com.movemate.shared.routes.CalculateRoute
import com.movemate.shared.routes.HomeRoute
import com.movemate.shared.routes.ProfileRoute
import com.movemate.shared.routes.ShipmentRoute
import com.movemate.shared.routes.SuccessRoute
import com.movemate.shared.viewmodel.MoveMateSharedViewModel
import com.movemate.shared.viewmodel.MovemateGlobalAppState
import com.movemate.shipment.presentation.screen.ShipmentScreen
import com.simon.movemate.ui.navigation.bottomNav.HomeBottomNavigation

const val animationDuration = 800

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
                                easing = LinearOutSlowInEasing
                            )
                        ) { it } + fadeIn(tween(animationDuration)),
                        exit = slideOutVertically(
                            tween(
                                animationDuration,
                                easing = EaseIn
                            )
                        ) { it } + fadeOut(tween(animationDuration)),
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


                        composable<HomeRoute>(
                            enterTransition = { slideInVertically { -(it / 2) } + fadeIn() }
                        ) {
                            HomeScreen()
                        }

                        composable<CalculateRoute>(
                            enterTransition = { slideInVertically { it / 2 } + fadeIn() }
                        ) {
                            CalculateScreen(onNavigateBack = {
                                navController.navigateUp()
                            }) {
                                navController.navigate(it)
                            }
                        }


                        composable<ShipmentRoute>(
                            enterTransition = { slideInVertically { it / 2 } + fadeIn() },
                            exitTransition = { slideOutVertically { it / 2 } + fadeOut() }
                        ) {
                            ShipmentScreen{
                                navController.navigateUp()
                            }
                        }
                        composable<ProfileRoute>() {
                            Box() {
                                Text("")
                            }
                        }

                        composable<SuccessRoute>(
                            enterTransition = { slideInVertically { it / 2 } + fadeIn() },
                            exitTransition = { slideOutVertically { it / 2 } + fadeOut() },
                        ) {
                            CalculateSuccessContent {
                                navController.navigate(HomeRoute)
                            }
                        }


                    }
                }
            }
        }
    }
}