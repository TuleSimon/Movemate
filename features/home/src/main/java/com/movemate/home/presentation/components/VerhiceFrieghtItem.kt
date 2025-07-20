package com.movemate.home.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.EaseInOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.movemate.core.theme.base.MovemateColors
import com.movemate.core.theme.base.MovemateTheme
import com.movemate.core.theme.base.MovemateTypes
import com.movemate.core.theme.responsiveness.hdp
import com.movemate.core.theme.responsiveness.wdp
import com.movemate.core.theme.responsiveness.wsp
import com.movemate.core.utils.shadowCustom
import com.movemate.home.R
import kotlinx.coroutines.delay

@Composable
fun LazyItemScope.FreightItem(
    modifier: Modifier = Modifier,
    name: String,
    status: String,
    image: Int,
    isListVisible: Boolean,
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.MovemateColors.cardColor
        ),
        shape = RoundedCornerShape(14.wdp),
        modifier = modifier.animateItem()
    ) {
        Column(
            modifier = modifier
                .width(120.wdp)
                .height(150.wdp)

        ) {
            Column(Modifier.padding(horizontal = 10.wdp,vertical = 10.hdp)) {
                Text(
                    text = name,
                    style = MaterialTheme.MovemateTypes.titleTextSmall.copy(
                        fontWeight = FontWeight.Normal,
                        fontSize = 15.wsp
                    ),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
                Text(
                    text = status,
                    style = MaterialTheme.MovemateTypes.bodyTextMediumNormal,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
            }
            Box(
                Modifier
                    .clipToBounds()
                    .clip(RoundedCornerShape(16.dp))
            ) {
                val shouldAnimate = remember {
                    mutableStateOf(false)
                }

                LaunchedEffect(isListVisible) {
                    if(isListVisible){
                        delay(300)
                        shouldAnimate.value=true
                    }
                }

                this@Column.AnimatedVisibility(visible  = shouldAnimate.value,
                    enter = slideInHorizontally(tween(400,
                        easing = EaseInOut)) { it }
                            + fadeIn(tween(400))
                ) {
                    Image(
                        painter = painterResource(id = image),
                        contentDescription = "freight image",
                        modifier = Modifier
                            .fillMaxSize()
                            .offset(x = 40.dp)
                            .scale(1.2f)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun VehicePreview(){
    MovemateTheme {
        LazyRow {
            item {
                FreightItem(
                    modifier = Modifier,
                    name = "Selena Keller",
                    status = "inani",
                    image = R.drawable.sender_package,
                    isListVisible = true
                )
            }
        }
    }
}