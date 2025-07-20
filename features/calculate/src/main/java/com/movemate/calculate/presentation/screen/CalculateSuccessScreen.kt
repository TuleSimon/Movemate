package com.movemate.calculate.presentation.screen

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.movemate.calculate.R
import com.movemate.calculate.presentation.components.FormSection
import com.movemate.calculate.presentation.components.TopAppBar
import com.movemate.calculate.presentation.viewModels.CalculateScreenAction
import com.movemate.calculate.presentation.viewModels.CalculateScreenState
import com.movemate.calculate.presentation.viewModels.CalculateScreenViewModel
import com.movemate.core.components.MovemateFilledButton
import com.movemate.core.theme.base.MovemateColors
import com.movemate.core.theme.base.MovemateTypes
import com.movemate.core.theme.base.defPadding
import com.movemate.core.theme.colors.MovemateColors
import com.movemate.core.theme.responsiveness.hdp
import com.movemate.core.theme.responsiveness.wdp
import com.movemate.core.theme.responsiveness.wsp
import com.movemate.core.theme.typography.roboto
import com.movemate.core.theme.typography.rubik
import com.movemate.shared.ChangeNavigationBarColors
import com.movemate.shared.LocalSharedTransitionScope
import com.movemate.shared.getSharedViewModel
import com.movemate.shared.viewmodel.MoveMateActions
import com.movemate.shared.viewmodel.MoveMateSharedViewModel
import kotlinx.coroutines.delay


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun AnimatedVisibilityScope.CalculateSuccessContent(
    sharedViewModel: MoveMateSharedViewModel = getSharedViewModel<MoveMateSharedViewModel>(),
    onNavigateBack: () -> Unit,
) {
    val scope = LocalSharedTransitionScope.current!!
    ChangeNavigationBarColors(
        lightStatusBarColor = MaterialTheme.MovemateColors.primary,
        darkStatusBarColor = MaterialTheme.MovemateColors.primary,
        navigationStatusBarColor = MaterialTheme.MovemateColors.cardColor,
        navigationDarkStatusBarColor = MaterialTheme.MovemateColors.cardColor,
        isDarkk = isSystemInDarkTheme()
    )

    var cost by remember { mutableIntStateOf(1000) }
    val animatedCost by animateIntAsState(
        targetValue = cost,
        label = "cost",
        animationSpec = tween(durationMillis = 2500)
    )
    LaunchedEffect("cost") {
        cost = 1423
    }

    DisposableEffect(true) {
        onDispose {
            sharedViewModel.handleAction(MoveMateActions.OpenBottomNavigation)
        }
    }

    Column(
        Modifier
            .padding(horizontal = defPadding)
            .statusBarsPadding()
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val preloaderLottieComposition by rememberLottieComposition(
            LottieCompositionSpec.RawRes(
                R.raw.movemate
            )
        )

        val preloaderProgress by animateLottieCompositionAsState(
            preloaderLottieComposition,
            iterations = 1,
            isPlaying = true
        )


        LottieAnimation(
            composition = preloaderLottieComposition,
            progress = { preloaderProgress },
            modifier = Modifier.fillMaxWidth(0.7f)
        )

        Image(
            painter = painterResource(R.drawable.box_shadow),
            modifier = Modifier.fillMaxWidth(0.6f),
            contentDescription = null
        )
        Spacer(modifier = Modifier.size(40.hdp))
        Text(
            text = stringResource(R.string.total_estimated_amount),
            style = MaterialTheme.MovemateTypes.bodyTextLargeNormal.copy(
                color = MaterialTheme.MovemateColors.textColorHeader,
                fontSize = 24.wsp,
            ),
        )

        Spacer(modifier = Modifier.size(12.dp))
        Row(
            verticalAlignment = Alignment.Bottom
        ) {
            Text(
                text = "$$animatedCost",
                fontFamily = roboto,
                style = MaterialTheme.MovemateTypes.titleTextLarge.copy(
                    color = MovemateColors.green,
                    fontWeight = FontWeight.Normal
                ),
                fontSize = 25.wsp
            )
            Text(
                text = stringResource(R.string.usd),
                style = MaterialTheme.MovemateTypes.titleTextLarge.copy(
                    color = MovemateColors.green,
                    fontWeight = FontWeight.Normal
                ),
                modifier = Modifier.padding(bottom = 1.hdp),
                fontSize = 20.wsp
            )
        }
        Spacer(modifier = Modifier.size(12.dp))
        Text(
            text = stringResource(R.string.this_amount_is_estimated_this_will_vary_if_you_change_your_location_or_weight),
            textAlign = TextAlign.Center,
            style = MaterialTheme.MovemateTypes.bodyTextLargeNormal.copy(
                fontSize = 14.wsp,
                lineHeight = 20.wsp
            ),
            modifier = Modifier,

            )
        Spacer(modifier = Modifier.size(34.dp))
        with(scope) {
            MovemateFilledButton(
                stringResource(R.string.back_to_home),
                modifier = Modifier
                    .fillMaxWidth()
                    .sharedElement(
                        rememberSharedContentState("Button"),
                        this@CalculateSuccessContent
                    )
            ) {
                sharedViewModel.handleAction(MoveMateActions.OpenBottomNavigation)
                onNavigateBack()
            }
        }

    }
}