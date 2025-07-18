package com.simon.movemate.ui.navigation.bottomNav

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.EaseInBounce
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.movemate.core.theme.base.MovemateColors
import com.movemate.core.theme.base.MovemateTheme
import com.movemate.core.theme.base.MovemateTypes
import com.movemate.core.theme.responsiveness.hdp
import com.movemate.core.theme.responsiveness.wdp
import com.movemate.core.theme.responsiveness.wsp
import com.movemate.shared.routes.HomeRoute
import com.movemate.shared.routes.TopLevelRoute
import com.simon.movemate.R

@Composable
fun BottomNavMenuItem(
    modifier: Modifier = Modifier,
    item: TopLevelRoute<out Any>,
    isActive: Boolean = false,
    onNavigate: () -> Unit = {}
) {

    val colorNormal = MaterialTheme.MovemateColors.textColor
    val colorActivated = MaterialTheme.MovemateColors.primary

    val colorTint = animateColorAsState(if (isActive) colorActivated else colorNormal)

    var isPressed by remember { mutableStateOf(false) }

    /**
     * Scale animation for icons when pressed
     */
    val scale by animateFloatAsState(
        targetValue = if (isPressed) 1.2f else 1f,
        animationSpec = tween(durationMillis = 150, easing = EaseInBounce),
        label = "scaleAnimation"
    )

    Column(
        modifier
            .pointerInput(Unit) {
                detectTapGestures(onPress = {
                    isPressed = true
                    try {
                        awaitRelease()
                    } finally {
                        isPressed = false
                    }
                }, onTap = {
                    onNavigate()
                })
            }
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Icon(
            painter = painterResource(item.icon),
            contentDescription = stringResource(item.name),
            modifier = Modifier.size(24.wdp),
            tint = colorTint.value
        )
        Spacer(Modifier.height(5.hdp))
        Text(
            stringResource(item.name), style = MaterialTheme.MovemateTypes.bodyTextSmallNormal.copy(
                color = colorTint.value, fontSize = 12.wsp
            )
        )

    }


}


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PreviewBottomNav() {
    MovemateTheme {
        BottomNavMenuItem(
            Modifier
                .navigationBarsPadding()
                .statusBarsPadding(),
            TopLevelRoute<HomeRoute>(
                name = (R.string.home), route = HomeRoute, icon = R.drawable.icon_bottom_home
            ),
            true,
        ) {

        }
    }
}



