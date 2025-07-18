package com.movemate.home.presentation.components

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.EaseInExpo
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.movemate.core.theme.base.MovemateColors
import com.movemate.core.theme.base.MovemateTheme
import com.movemate.core.theme.base.MovemateTypes
import com.movemate.core.theme.base.defPadding
import com.movemate.core.theme.responsiveness.hdp
import com.movemate.core.theme.responsiveness.wdp
import com.movemate.core.theme.responsiveness.wsp
import com.movemate.home.R
import com.movemate.home.presentation.viewModels.HomeScreenAction
import com.movemate.shared.viewmodel.MoveMateActions

@Composable
fun TopAppBar(
    query: String = "",
    isSearchMode: Boolean = false,
    onAction: (HomeScreenAction) -> Unit,
    onGlobalAction: (MoveMateActions) -> Unit,
) {

    val focusRequester = remember {
        FocusRequester()
    }
    val localFocusRequester = LocalFocusManager.current

    BackHandler(isSearchMode) {
        localFocusRequester.clearFocus(true)
        focusRequester.freeFocus()
        onAction(HomeScreenAction.toggleSearchMode(false))
    }

    Column(
        Modifier
            .fillMaxWidth()
            .background(MaterialTheme.MovemateColors.primary)
            .animateContentSize()
            .padding(horizontal = defPadding)
            .statusBarsPadding()
    ) {
        AnimatedVisibility(isSearchMode.not()) {
            Column {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    AsyncImage(
                        R.drawable.img,
                        modifier = Modifier
                            .size(37.wdp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop,
                        contentDescription = "Avatar"
                    )
                    Spacer(Modifier.width(10.wdp))
                    Column(Modifier.weight(1f)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(5.wdp)
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.icon_location),
                                tint = MaterialTheme.MovemateColors.textColor,
                                contentDescription = "Location",
                                modifier = Modifier.size(11.wdp)
                            )
                            Text(
                                stringResource(R.string.your_location),
                                style = MaterialTheme.MovemateTypes.bodyTextSmallNormal.copy(
                                    fontSize = 12.wsp,
                                    color = MaterialTheme.MovemateColors.onCardColor.copy(0.8f),
                                )
                            )
                        }
                        Spacer(Modifier.height(5.hdp))
                        Text(
                            stringResource(R.string.wertheimer_illinois),
                            style = MaterialTheme.MovemateTypes.bodyTextMedium.copy(
                                fontSize = 15.wsp,
                                color = MaterialTheme.MovemateColors.onCardColor.copy(0.8f),
                                fontWeight = FontWeight.Normal
                            )
                        )
                    }
                    Spacer(Modifier.width(10.wdp))
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .background(MaterialTheme.MovemateColors.cardColor, CircleShape)
                            .clip(CircleShape)
                            .padding(8.wdp)
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.icon_notification),
                            tint = MaterialTheme.MovemateColors.textColorHeader,
                            contentDescription = "Notification",
                            modifier = Modifier.size(20.wdp)
                        )
                    }

                }

                Spacer(Modifier.height(15.hdp))
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
        ) {

            if (
                isSearchMode
            ) {
                Icon(
                    painter = painterResource(R.drawable.icon_back),
                    tint = MaterialTheme.MovemateColors.textColor,
                    contentDescription = "Back",
                    modifier = Modifier
                        .size(20.wdp)
                        .clickable {
                            localFocusRequester.clearFocus(true)
                            focusRequester.freeFocus()
                            onAction(HomeScreenAction.toggleSearchMode(false))
                        }
                )

                Spacer(Modifier.width(5.wdp))
            }

            MovemateSearchBar(
                value = query,
                focus = focusRequester,
                onValueChange = {
                    onAction(HomeScreenAction.updateQuery(it))
                }, onFocusChanged = {
                    onAction(HomeScreenAction.toggleSearchMode(it))
                    onGlobalAction(if (it) MoveMateActions.CloseBottomNavigation
                    else MoveMateActions.OpenBottomNavigation)
                })
        }

        Spacer(Modifier.height(20.hdp))
    }

}

@Preview(showBackground = true)
@Composable
private fun TopAppbarPreview() {
    MovemateTheme {
        TopAppBar(onAction = {}) {

        }
    }
}
