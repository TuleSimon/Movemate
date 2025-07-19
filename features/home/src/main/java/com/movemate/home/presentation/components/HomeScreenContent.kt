package com.movemate.home.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.EaseInCubic
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.movemate.core.theme.responsiveness.hdp


@Composable
fun HomeScreenInitialContent(isVisible:Boolean=false){
    AnimatedVisibility(isVisible,
        enter = slideInVertically(tween(500, easing = EaseInCubic)){it}) {
        Column {
            TrackingDetailsSection()
            Spacer(Modifier.height(15.hdp))
            AvailableVehiclesSection(vehicles = dummyVehiclesList, isVisible = isVisible)
        }
    }
}