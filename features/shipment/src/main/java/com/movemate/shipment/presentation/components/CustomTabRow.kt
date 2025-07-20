package com.movemate.shipment.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SecondaryScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.movemate.core.components.bounceClick
import com.movemate.core.theme.base.MovemateColors
import com.movemate.core.theme.base.MovemateTheme
import com.movemate.core.theme.base.MovemateTypes
import com.movemate.core.theme.responsiveness.hdp
import com.movemate.core.theme.responsiveness.wdp
import com.movemate.core.theme.responsiveness.wsp
import com.movemate.shipment.presentation.viewModels.ShipmentStatusLabel
import com.movemate.shipment.presentation.viewModels.listOfShipmentStatus


@Composable
fun CustomTabRow(
    selectedId: Int, modifier: Modifier = Modifier,
    onSelect: (Int) -> Unit
) {
    val shouldAnimate = remember {
        mutableStateOf(false)
    }

    LaunchedEffect(true) {
        shouldAnimate.value = true
    }

    AnimatedVisibility(shouldAnimate.value,
        enter = fadeIn(tween(400)) + slideInHorizontally(tween(400)){it/2},
        exit = fadeOut(tween(400)) + slideOutHorizontally(tween(400)),
    ) {
        SecondaryScrollableTabRow(
            modifier = modifier,
            selectedTabIndex = selectedId,
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = Color.White,
            edgePadding = 0.dp,
            divider = {},
            indicator = { ->
                TabRowDefaults.SecondaryIndicator(
                    Modifier
                        .padding(top = 8.hdp)
                        .tabIndicatorOffset(
                            ShipmentStatusLabel.entries.indexOfFirst { it.id == selectedId },
                            matchContentSize = true
                        ),
                    color = MaterialTheme.MovemateColors.secondary
                )
            }
        ) {
            ShipmentStatusLabel.entries.forEach { status ->
                val isSelected = selectedId == status.id
                val count =
                    if (status == ShipmentStatusLabel.ALL) listOfShipmentStatus.size else listOfShipmentStatus.count { it.status == status }
                Tab(
                    selected = isSelected,
                    onClick = {
                        onSelect(status.id)
                    },
                    modifier = Modifier.bounceClick()
                ) {
                    Row(modifier = Modifier.padding(horizontal = 17.dp, vertical = 8.wdp)) {
                        Text(
                            text = status.title,
                            color = if (isSelected) MaterialTheme.MovemateColors.cardColor else MaterialTheme.MovemateColors.cardColor.copy(
                                0.6f
                            ),
                            modifier = Modifier.padding(end = 8.dp),
                            style = MaterialTheme.MovemateTypes.bodyTextSmallNormal.copy(
                                color = if (isSelected) MaterialTheme.MovemateColors.cardColor else MaterialTheme.MovemateColors.cardColor.copy(
                                    0.6f
                                )
                            ),
                            fontSize = 15.wsp
                        )
                        Box(
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(
                                    color = if (isSelected) MaterialTheme.MovemateColors.secondary else Color.White.copy(
                                        0.2f
                                    )
                                )
                                .padding(3.wdp)
                                .aspectRatio(1.4f),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "$count",
                                style = MaterialTheme.MovemateTypes.bodyTextSmallNormal.copy(
                                    color = if (isSelected) MaterialTheme.MovemateColors.cardColor else MaterialTheme.MovemateColors.cardColor.copy(
                                        0.6f
                                    )
                                ),
                            )
                        }

                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun Preview() {
    MovemateTheme {
        CustomTabRow(0) { }
    }
}