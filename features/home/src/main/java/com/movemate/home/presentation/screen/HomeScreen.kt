package com.movemate.home.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.movemate.core.theme.base.MovemateColors
import com.movemate.home.presentation.components.TopAppBar
import com.movemate.home.presentation.viewModels.HomeScreenAction
import com.movemate.home.presentation.viewModels.HomeScreenState
import com.movemate.home.presentation.viewModels.HomeScreenViewModel
import com.movemate.shared.ChangeNavigationBarColors
import com.movemate.shared.getSharedViewModel
import com.movemate.shared.viewmodel.MoveMateActions
import com.movemate.shared.viewmodel.MoveMateSharedViewModel


@Composable
fun HomeScreen(homeViewModel: HomeScreenViewModel = hiltViewModel<HomeScreenViewModel>(),
               sharedViewModel: MoveMateSharedViewModel = getSharedViewModel()
) {

    val state = homeViewModel.homeScreenState.collectAsStateWithLifecycle()

    HomeScreenContent(state.value,
        homeViewModel::handleHomeScreenAction,
        sharedViewModel::handleAction)

}


@Composable
private fun HomeScreenContent(
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

    Column {
        TopAppBar(state.query, state.searchMode, onAction,onGlobalAction)
    }
}