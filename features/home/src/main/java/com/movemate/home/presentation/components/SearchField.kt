package com.movemate.home.presentation.components


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.movemate.core.theme.base.MovemateColors
import com.movemate.core.theme.base.MovemateTheme
import com.movemate.core.theme.base.MovemateTypes
import com.movemate.core.theme.responsiveness.hdp
import com.movemate.core.theme.responsiveness.wdp
import com.movemate.core.theme.responsiveness.wsp
import com.movemate.home.R
import com.movemate.shared.logger.MovemateLogger

@Composable
fun MovemateSearchBar(
    modifier: Modifier = Modifier,
    value: String = "",
    focus: FocusRequester = FocusRequester(),
    placeholderText: String = "Enter the receipt number ...",
    onValueChange: (String) -> Unit = {},
    onFocusChanged: (Boolean) -> Unit = {}
) {
    var isFocused by remember { mutableStateOf(false) }
    val interactionSource = remember {
        MutableInteractionSource()
    }
    val flows = interactionSource.collectIsFocusedAsState()
    LaunchedEffect(flows.value) {
        MovemateLogger.d("Interaction ${flows.value}")
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .animateContentSize(tween(400, easing = FastOutLinearInEasing))
            .background(
                color = MaterialTheme.MovemateColors.cardColor,
                shape = RoundedCornerShape(100f)
            )
            .padding(vertical = 5.hdp)
            .padding(start = 10.wdp, end = 5.wdp),
        contentAlignment = Alignment.CenterStart
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.icon_search),
                contentDescription = "Search",
                modifier = Modifier
                    .size(18.wdp),
                tint = MaterialTheme.MovemateColors.textColor
            )

            Spacer(modifier = Modifier.width(8.wdp))

            Box(modifier = Modifier.weight(1f)) {
                this@Row.AnimatedVisibility(value.isEmpty() && isFocused.not()) {
                    Text(
                        text = placeholderText,
                        style = MaterialTheme.MovemateTypes.bodyTextSmallNormal.copy(
                            color = MaterialTheme.MovemateColors.textColor,
                            fontSize = 13.wsp
                        ),
                        modifier = Modifier.alpha(0.5f)
                    )
                }

                BasicTextField(
                    value = value,
                    onValueChange = {
                        onValueChange(it)
                    },
                    singleLine = true,
                    textStyle = MaterialTheme.MovemateTypes.bodyTextSmallNormal.copy(
                        color = MaterialTheme.MovemateColors.textColor,
                        fontSize = 13.wsp
                    ),
                    cursorBrush = SolidColor(MaterialTheme.MovemateColors.textColorHeader),
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(focus)
                        .onFocusChanged {
                            MovemateLogger.d("On focus ${it.isFocused} ${flows.value}")
                            onFocusChanged(it.isFocused)
                            isFocused = it.isFocused
                        }

                )
            }

            Spacer(modifier = Modifier.width(8.wdp))

            Box(
                modifier = Modifier
                    .background(MaterialTheme.MovemateColors.secondary, CircleShape)
                    .padding(5.wdp)
            ) {
                Icon(
                    painter = painterResource(com.movemate.movemate.R.drawable.icon_scanner),
                    contentDescription = "Scan",
                    modifier = Modifier.size(22.wdp),
                    tint = MaterialTheme.MovemateColors.cardColor
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewMovemateSearchBar() {
    MovemateTheme {
        MovemateSearchBar(
            modifier = Modifier.padding(16.wdp)
        )
    }
}
