package com.simon.movemate.ui.navigation.bottomNav

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.movemate.core.theme.base.MovemateColors
import com.movemate.core.theme.base.MovemateTheme
import com.movemate.core.theme.responsiveness.hdp
import com.movemate.core.theme.responsiveness.wdp

val LocalHomeBottomNavController = compositionLocalOf<NavHostController> {
    error("LocalHomeBottomNavController is not initialized")
}


@Composable
fun HomeBottomNavigation(navController: NavController, modifier: Modifier = Modifier) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()

    val currentDestination = navBackStackEntry?.destination

    val selectedIndex = remember(currentDestination) {
        derivedStateOf {
            BOTTOM_MENUS.indexOfFirst { menu ->
                currentDestination?.hierarchy?.any { it.hasRoute(menu.route::class) } == true
            }.takeIf { it != -1 } ?: 0
        }
    }

    val itemSpacing = 20.wdp
    val density = LocalDensity.current

    val barWidthPx = remember {
        mutableFloatStateOf(0f)
    }

    val barOffset by animateFloatAsState(
        targetValue = (selectedIndex.value * barWidthPx.floatValue) + (selectedIndex.value * with(
            density
        ) { itemSpacing.toPx() }),
        animationSpec = tween(durationMillis = 300),
        label = "bottomNavIndicatorOffset"
    )

    Box(Modifier
        .fillMaxWidth()
        .background(MaterialTheme.MovemateColors.cardColor)) {

        Box(
            Modifier
                .graphicsLayer {
                    translationX = barOffset
                }
                .background(MaterialTheme.MovemateColors.primary)
                .width(with(density) { barWidthPx.floatValue.toDp() })
                .height(3.hdp)
                .align(Alignment.TopStart)
        )

        Row(
            modifier = modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .padding(
                    vertical = 18.dp,
                ),
            horizontalArrangement = Arrangement.spacedBy(itemSpacing),
            verticalAlignment = Alignment.CenterVertically,
        ) {

            BOTTOM_MENUS.forEachIndexed { index, screen ->

                val selected = index == selectedIndex.value

                BottomNavMenuItem(
                    Modifier
                        .weight(1f)
                        .onSizeChanged {
                            if (barWidthPx.floatValue == 0f) {
                                barWidthPx.floatValue = it.width.toFloat()
                            }
                        }, screen, selected
                ) {
                    navController.navigate(screen.route) {

                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true

                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun PreviewBottomNavigation() {
    MovemateTheme {
        HomeBottomNavigation(rememberNavController())
    }
}

