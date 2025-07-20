package com.movemate.shipment.presentation.screen

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.core.EaseInElastic
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.movemate.core.theme.base.MovemateColors
import com.movemate.core.theme.base.MovemateTypes
import com.movemate.core.theme.base.defPadding
import com.movemate.core.theme.responsiveness.hdp
import com.movemate.core.theme.responsiveness.wsp
import com.movemate.shipment.R
import com.movemate.shipment.presentation.components.ShipmentItem
import com.movemate.shipment.presentation.components.TopAppBar
import com.movemate.shipment.presentation.viewModels.ShipmentScreenViewModel
import com.movemate.shipment.presentation.viewModels.ShipmentState
import com.movemate.shipment.presentation.viewModels.listOfShipmentStatus

@Composable
fun AnimatedVisibilityScope.ShipmentScreen(
    shipmentScreenViewModel: ShipmentScreenViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit
) {
    val state = shipmentScreenViewModel.shipmentState.collectAsStateWithLifecycle()
    ShipmentScreenContent(
        state.value,
        { shipmentScreenViewModel.updateSelectedShipmentTab(it) }) {
        onNavigateBack()
    }
}


@Composable
private fun AnimatedVisibilityScope.ShipmentScreenContent(
    state: ShipmentState,
    onUpdateSelectedShipment: (Int) -> Unit,
    onNavigateBack: () -> Unit
) {
    val shouldAnimate = remember {
        mutableStateOf(false)
    }

    LaunchedEffect(true) {
        shouldAnimate.value = true
    }

    Column {
        TopAppBar(state, onUpdateSelectedShipment, onNavigateBack)
        Spacer(Modifier.height(15.hdp))
        Text(
            stringResource(R.string.shipments),
            style = MaterialTheme.MovemateTypes.titleTextSmall.copy(
                color = MaterialTheme.MovemateColors.textColorHeader.copy(0.9f),
                fontSize = 17.wsp,
                fontWeight = FontWeight.SemiBold
            ),
            modifier = Modifier.padding(horizontal = defPadding)
        )
        Spacer(Modifier.height(15.hdp))


        AnimatedVisibility(
            shouldAnimate.value,
            enter = fadeIn(tween(400)) + slideInVertically(
                tween(
                    400,
                    easing = EaseInOut
                )
            ){ height -> height / 2 } ,
            exit = fadeOut(tween(400)) + slideOutVertically(tween(400)),
        ) {
            AnimatedContent(
                state.selectedTabId,
                transitionSpec = {
                    slideInVertically(
                        tween(
                            400,
                            easing = EaseInOut
                        )
                    ) { height -> height / 2 } + fadeIn(
                        tween(300)
                    ) togetherWith
                            slideOutVertically { height -> height } + fadeOut()
                }) { category ->
                val items = remember(category) {
                    if (category == 0) listOfShipmentStatus else listOfShipmentStatus.filter { it.status.id == category }
                }
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(15.hdp),
                    modifier = Modifier.padding(horizontal = defPadding)
                ) {

                    itemsIndexed(
                        items,
                        key = { index, _ -> index }) { index, item ->
                        ShipmentItem(item)
                    }
                }
            }
        }
    }
}


@Preview
@Composable
private fun ShipmentScreenPreview() {
    AnimatedVisibility(true) {
        ShipmentScreenContent(ShipmentState(), {}) {}
    }
}