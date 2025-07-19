package com.movemate.calculate.presentation.screen

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.movemate.calculate.presentation.components.TopAppBar
import com.movemate.calculate.presentation.viewModels.CalculateScreenAction
import com.movemate.calculate.presentation.viewModels.CalculateScreenState
import com.movemate.calculate.presentation.viewModels.CalculateScreenViewModel
import com.movemate.core.theme.base.MovemateColors
import com.movemate.core.theme.responsiveness.hdp
import com.movemate.shared.ChangeNavigationBarColors


@Composable
fun AnimatedVisibilityScope.CalculateScreen(
    calculateViewModel: CalculateScreenViewModel = hiltViewModel<CalculateScreenViewModel>(),
    onNavigateBack: () -> Unit,
) {

    val state = calculateViewModel.calculateScreenState.collectAsStateWithLifecycle()

    CalculateScreenContent(
        state.value,
        calculateViewModel::handleHomeScreenAction,
        onNavigateBack
    )

}


@Composable
private fun AnimatedVisibilityScope.CalculateScreenContent(
    state: CalculateScreenState,
    onAction: (CalculateScreenAction) -> Unit,
    onNavigateBack: () -> Unit,
) {

    ChangeNavigationBarColors(
        lightStatusBarColor = MaterialTheme.MovemateColors.primary,
        darkStatusBarColor = MaterialTheme.MovemateColors.primary,
        navigationStatusBarColor = MaterialTheme.MovemateColors.cardColor,
        navigationDarkStatusBarColor = MaterialTheme.MovemateColors.cardColor,
    )

    Column(Modifier.navigationBarsPadding()) {
        TopAppBar(onAction, onNavigateBack)
        Spacer(Modifier.height(20.hdp))

    }
}