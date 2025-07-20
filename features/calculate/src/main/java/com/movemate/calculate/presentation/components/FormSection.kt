package com.movemate.calculate.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.movemate.calculate.R
import com.movemate.calculate.presentation.viewModels.CalculateScreenAction
import com.movemate.calculate.presentation.viewModels.CalculateScreenState
import com.movemate.core.components.MovemateFilledButton
import com.movemate.core.theme.base.MovemateColors
import com.movemate.core.theme.base.MovemateTheme
import com.movemate.core.theme.base.MovemateTypes
import com.movemate.core.theme.base.defPadding
import com.movemate.core.theme.responsiveness.hdp
import com.movemate.core.theme.responsiveness.wdp
import com.movemate.core.theme.responsiveness.wsp
import com.movemate.shared.LocalSharedTransitionScope
import kotlinx.coroutines.delay

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun AnimatedVisibilityScope.FormSection(
    state: CalculateScreenState,
    onAction: (CalculateScreenAction) -> Unit,
    onCalculate: () -> Unit
) {

    val scope = LocalSharedTransitionScope.current!!
    val shouldAnimateIn = remember {
        mutableStateOf(false)
    }
    LaunchedEffect(true) {
        delay(200)
        shouldAnimateIn.value = true
    }
    Column(
        Modifier.padding(
            horizontal = defPadding,
            vertical = 10.hdp
        )
    ) {
        Text(
            stringResource(R.string.destination),
            style = MaterialTheme.MovemateTypes.titleTextSmall.copy(
                color = MaterialTheme.MovemateColors.textColorHeader.copy(0.9f),
                fontSize = 17.wsp,
                fontWeight = FontWeight.SemiBold
            )
        )
        Spacer(Modifier.height(15.hdp))
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.MovemateColors.cardColor
            ),
            shape = RoundedCornerShape(15.wdp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                Modifier.padding(
                    horizontal = 10.wdp,
                    vertical = 10.hdp
                )
            ) {
                CustomTextField(state.senderLocation, {
                    onAction(CalculateScreenAction.UpdateSenderLocation(it))
                }, stringResource(R.string.sender_location), {
                    Icon(
                        painter = painterResource(R.drawable.icon_upload),
                        contentDescription = null,
                        tint = MaterialTheme.MovemateColors.textColor,
                        modifier = Modifier.size(20.wdp)
                    )
                })
                Spacer(Modifier.height(10.hdp))
                CustomTextField(state.receiverLocation, {
                    onAction(CalculateScreenAction.UpdateSenderLocation(it))
                }, stringResource(R.string.receiver_location), {
                    Icon(
                        painter = painterResource(R.drawable.icon_download),
                        contentDescription = null,
                        tint = MaterialTheme.MovemateColors.textColor,
                        modifier = Modifier.size(20.wdp)
                    )
                })
                Spacer(Modifier.height(10.hdp))
                CustomTextField(state.weight, {
                    onAction(CalculateScreenAction.UpdateSenderLocation(it))
                }, stringResource(R.string.approx_weight), {
                    Icon(
                        painter = painterResource(R.drawable.icon_scale),
                        contentDescription = null,
                        tint = MaterialTheme.MovemateColors.textColor,
                        modifier = Modifier.size(20.wdp)
                    )
                })
            }
        }
        Spacer(Modifier.height(25.hdp))
        Text(
            stringResource(R.string.packaging),
            style = MaterialTheme.MovemateTypes.titleTextSmall.copy(
                color = MaterialTheme.MovemateColors.textColorHeader.copy(0.9f),
                fontSize = 17.wsp,
                fontWeight = FontWeight.SemiBold
            )
        )
        Text(
            stringResource(R.string.what_are_you_sending),
            style = MaterialTheme.MovemateTypes.bodyTextSmallNormal.copy(
                color = MaterialTheme.MovemateColors.textColor.copy(0.9f),
                fontSize = 12.wsp
            )
        )
        Spacer(Modifier.height(10.hdp))
        CustomTextField(
            stringResource(R.string.box),
            onValChange = {

            },
            placeHolder = stringResource(R.string.what_are_you_sending),
            {
                Icon(
                    painter = painterResource(R.drawable.box),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier.size(20.wdp)
                )
            },
            backgroundColor = MaterialTheme.MovemateColors.cardColor,
            trailingIcon = {
                Icon(
                    painter = painterResource(R.drawable.icon_down),
                    contentDescription = null,
                    tint = MaterialTheme.MovemateColors.textColor,
                    modifier = Modifier.size(14.wdp)
                )
            },
            enabled = false,
        )
        Spacer(Modifier.height(25.hdp))
        Text(
            stringResource(R.string.packaging),
            style = MaterialTheme.MovemateTypes.titleTextSmall.copy(
                color = MaterialTheme.MovemateColors.textColorHeader.copy(0.9f),
                fontSize = 17.wsp,
                fontWeight = FontWeight.SemiBold
            )
        )
        Text(
            stringResource(R.string.what_are_you_sending),
            style = MaterialTheme.MovemateTypes.bodyTextSmallNormal.copy(
                color = MaterialTheme.MovemateColors.textColor.copy(0.9f),
                fontSize = 12.wsp
            )
        )
        Spacer(Modifier.height(10.hdp))
        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(10.wdp)
        ) {

            state.categories.forEachIndexed { index, category ->
                val isActive = state.selectedCategory == category
                AnimatedVisibility(
                    shouldAnimateIn.value,
                    enter = slideInHorizontally(
                        tween(
                            400,
                            delayMillis = index
                        )
                    ) {
                        it / 2
                    } + fadeIn(tween(400))
                ) {
                    AssistChip(
                        modifier = Modifier,
                        colors = AssistChipDefaults.assistChipColors(
                            containerColor = if (isActive) MaterialTheme.MovemateColors.textColorHeader
                            else Color.Transparent,
                            disabledContainerColor = Color.Transparent,
                            labelColor = MaterialTheme.MovemateColors.textColorHeader
                        ),
                        border = AssistChipDefaults.assistChipBorder(
                            true,
                            borderColor = MaterialTheme.MovemateColors.textColor
                        ),
                        onClick = {
                            onAction(CalculateScreenAction.UpdateSelectedCategory(category))
                        }, label = {
                            Row(
                                Modifier.animateContentSize(tween(300)),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                if (isActive) {
                                    Icon(
                                        painter = painterResource(R.drawable.icon_check_24),
                                        modifier = Modifier.size(14.wdp),
                                        tint = MaterialTheme.MovemateColors.cardColor,
                                        contentDescription = null
                                    )
                                    Spacer(Modifier.width(4.wdp))
                                }
                                Text(
                                    category,
                                    style = MaterialTheme.MovemateTypes.bodyTextSmallNormal.copy(
                                        color = if (isActive) MaterialTheme.MovemateColors.cardColor else MaterialTheme.MovemateColors.textColorHeader,
                                        fontSize = 12.wsp
                                    )
                                )
                            }

                        })
                }
            }
        }
        Spacer(Modifier.height(30.hdp))

        AnimatedVisibility(
            shouldAnimateIn.value,
            enter = slideInVertically(
                tween(
                    400
                )
            ) {
                it / 2
            } + fadeIn(tween(400))) {
            with(scope){
            MovemateFilledButton(
                stringResource(R.string.calculate),
                Modifier.fillMaxWidth().sharedElement(rememberSharedContentState("Button"),
                    this@FormSection)
            ) {
                onCalculate()
            }
        }
        }
    }
}


@Preview
@Composable
private fun PreviewForm() {
    MovemateTheme {
        AnimatedVisibility(true) {
            FormSection(CalculateScreenState(), {

            }) {}
        }
    }
}