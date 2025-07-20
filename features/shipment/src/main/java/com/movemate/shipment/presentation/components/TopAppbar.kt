package com.movemate.shipment.presentation.components


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.movemate.core.theme.base.MovemateColors
import com.movemate.core.theme.base.MovemateTheme
import com.movemate.core.theme.base.MovemateTypes
import com.movemate.core.theme.base.defPadding
import com.movemate.core.theme.responsiveness.hdp
import com.movemate.core.theme.responsiveness.wdp
import com.movemate.core.theme.responsiveness.wsp
import com.movemate.shared.LocalSharedTransitionScope
import com.movemate.shipment.R
import com.movemate.shipment.presentation.viewModels.ShipmentState
import kotlinx.coroutines.delay

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun AnimatedVisibilityScope.TopAppBar(
    state:ShipmentState,
    onUpdateSelectedShipment:(Int)-> Unit,
    onNavigateBack: () -> Unit
) {

    val isVisible = remember { mutableStateOf(false) }

    LaunchedEffect(true) {
        delay(100)
        isVisible.value = true
    }

    val sharedTransition = LocalSharedTransitionScope.current!!

    with(sharedTransition) {
        Column(
            Modifier
                .fillMaxWidth()
                .sharedBounds(
                    rememberSharedContentState("appbar"),
                    this@TopAppBar
                )
                .background(MaterialTheme.MovemateColors.primary)
                .animateContentSize()
                .padding(horizontal = defPadding)
                .statusBarsPadding()
        ) {

            Box(Modifier.fillMaxWidth()) {
                this@Column.AnimatedVisibility(
                    isVisible.value,
                    enter = slideInHorizontally(tween(500))
                ) {
                    IconButton(onClick = {
                        onNavigateBack()
                    }, modifier = Modifier.align(Alignment.CenterStart)) {
                        Icon(
                            painterResource(R.drawable.outline_arrow_back_ios_24),
                            modifier = Modifier.size(20.wdp),
                            tint = MaterialTheme.MovemateColors.cardColor,
                            contentDescription = null
                        )
                    }
                }

                Text(
                    stringResource(R.string.shipment_history),
                    style = MaterialTheme.MovemateTypes.bodyTextMedium.copy(
                        color = MaterialTheme.MovemateColors.cardColor,
                        fontSize = 16.wsp
                    ),
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            Spacer(Modifier.height(4.hdp))
            CustomTabRow(state.selectedTabId) { onUpdateSelectedShipment(it)}
        }
    }
}


@OptIn(ExperimentalSharedTransitionApi::class)
@Preview(showBackground = true)
@Composable
private fun TopAppbarPreview() {
    MovemateTheme {
        SharedTransitionLayout {
            CompositionLocalProvider(
                LocalSharedTransitionScope provides this,
            ) {
                AnimatedVisibility(true) {
                    TopAppBar(ShipmentState(),{}) {

                    }
                }
            }
        }
    }
}
