package com.movemate.home.presentation.screen

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.movemate.core.theme.base.MovemateColors
import com.movemate.core.theme.base.defPadding
import com.movemate.core.theme.responsiveness.hdp
import com.movemate.home.presentation.components.HomeScreenInitialContent
import com.movemate.home.presentation.components.SearchListItems
import com.movemate.home.presentation.components.TopAppBar
import com.movemate.home.presentation.viewModels.HomeScreenAction
import com.movemate.home.presentation.viewModels.HomeScreenState
import com.movemate.home.presentation.viewModels.HomeScreenViewModel
import com.movemate.shared.ChangeNavigationBarColors
import com.movemate.shared.getSharedViewModel
import com.movemate.shared.viewmodel.MoveMateActions
import com.movemate.shared.viewmodel.MoveMateSharedViewModel


@Composable
fun AnimatedVisibilityScope.HomeScreen(
    homeViewModel: HomeScreenViewModel = getSharedViewModel<HomeScreenViewModel>(),
    sharedViewModel: MoveMateSharedViewModel = getSharedViewModel()
) {

    val state = homeViewModel.homeScreenState.collectAsStateWithLifecycle()

    HomeScreenContent(
        state.value,
        homeViewModel::handleHomeScreenAction,
        sharedViewModel::handleAction
    )

}


@Composable
private fun AnimatedVisibilityScope.HomeScreenContent(
    state: HomeScreenState,
    onAction: (HomeScreenAction) -> Unit,
    onGlobalAction: (MoveMateActions) -> Unit,
) {

    ChangeNavigationBarColors(
        lightStatusBarColor = MaterialTheme.MovemateColors.primary,
        darkStatusBarColor = MaterialTheme.MovemateColors.primary,
        navigationStatusBarColor = MaterialTheme.MovemateColors.cardColor,
        navigationDarkStatusBarColor = MaterialTheme.MovemateColors.cardColor,
    )

    Column(Modifier.navigationBarsPadding()) {
        TopAppBar(state.query, state.searchMode, state.showAppBar, onAction, onGlobalAction)
        Spacer(Modifier.height(20.hdp))
        AnimatedContent(
            state.searchMode,
            modifier = Modifier.padding(horizontal = defPadding),
            transitionSpec = {
                (fadeIn(animationSpec = tween(520, delayMillis = 90)) +
                        slideInVertically(
                            animationSpec = tween(
                                420,
                                easing = FastOutSlowInEasing
                            )
                        ) {
                            it / 2
                        })
                    .togetherWith(
                        fadeOut(animationSpec = tween(420)) +
                                slideOutVertically(animationSpec = tween(420)) {
                                    it / 2
                                })
            }) {
            if (it) {
                SearchListItems(Modifier.wrapContentHeight(), query = state.query)
            } else {
                HomeScreenInitialContent(state.searchMode.not())
            }
        }
    }
}