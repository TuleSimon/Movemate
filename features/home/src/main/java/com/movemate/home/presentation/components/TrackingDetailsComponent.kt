package com.movemate.home.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.movemate.core.theme.base.MovemateColors
import com.movemate.core.theme.base.MovemateTheme
import com.movemate.core.theme.base.MovemateTypes
import com.movemate.core.theme.responsiveness.hdp
import com.movemate.core.theme.responsiveness.wdp
import com.movemate.core.theme.responsiveness.wsp
import com.movemate.core.utils.shadowCustom
import com.movemate.home.R

@Composable
fun TrackingDetailsSection(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.tracking),
            style = MaterialTheme.MovemateTypes.titleTextSmall.copy(
                fontSize = 18.wsp,
                fontWeight = FontWeight.SemiBold
            )
        )
        Spacer(modifier = Modifier.height(14.hdp))
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.MovemateColors.cardColor
            ),
            shape = RoundedCornerShape(14.wdp),
            modifier = modifier.shadowCustom()
        ) {
            Column(Modifier.padding(horizontal = 10.wdp)) {
                Spacer(modifier = Modifier.height(10.hdp))
                Row(
                    modifier = Modifier.padding(horizontal = 10.hdp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = stringResource(R.string.shipment_number),
                            style = MaterialTheme.MovemateTypes.bodyTextMedium
                        )
                        Spacer(Modifier.height(5.hdp))
                        Text(
                            text = stringResource(R.string.nej200089934122231),
                            style = MaterialTheme.MovemateTypes.titleTextSmall.copy(
                                fontSize = 16.wsp,
                                fontWeight = FontWeight.SemiBold
                            )
                        )
                    }
                    Spacer(Modifier.width(5.wdp))
                    Image(
                        painter = painterResource(id = R.drawable.fork_lift),
                        contentDescription = "fork lift icon",
                        modifier = Modifier.size(40.wdp)
                    )
                }

                HorizontalDivider(
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 13.hdp),
                    color = MaterialTheme.MovemateColors.textColor.copy(0.2f)
                )

                TrackingItem(
                    modifier = Modifier
                        .padding(horizontal = 10.wdp),
                    trackingType = TrackingType.SENDER,
                    address = "Atlanta, 5243",
                    duration = "2 day -3 days"
                )
                Spacer(modifier = Modifier.height(30.hdp))
                TrackingItem(
                    modifier = Modifier
                        .padding(horizontal = 10.wdp),
                    trackingType = TrackingType.RECEIVER,
                    address = "Chicago, 6342",
                    duration = "Waiting to collect"
                )
                HorizontalDivider(
                    Modifier
                        .fillMaxWidth()
                        .fillMaxWidth()
                        .padding(vertical = 13.hdp),
                    color = MaterialTheme.MovemateColors.textColor.copy(0.2f)
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Rounded.Add,
                        contentDescription = "add",
                        tint = MaterialTheme.MovemateColors.secondary,
                        modifier = Modifier.size(20.wdp),
                    )
                    Text(
                        text = "Add Stop",
                        style = MaterialTheme.MovemateTypes.bodyTextSmall.copy(
                            color = MaterialTheme.MovemateColors.secondary,
                        )
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}

@Composable
private fun TrackingItem(
    modifier: Modifier = Modifier,
    trackingType: TrackingType,
    address: String,
    duration: String
) {
    val color = if (trackingType == TrackingType.SENDER) Color(0xFFfce5d4) else Color(0xFFd4fcda)
    val type =
        if (trackingType == TrackingType.SENDER) stringResource(R.string.sender) else stringResource(
            R.string.receiver
        )
    val detail =
        if (trackingType == TrackingType.SENDER) stringResource(R.string.time) else stringResource(
            R.string.status
        )
    val box =
        if (trackingType == TrackingType.SENDER) R.drawable.sender_package else R.drawable.receiver_package

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .background(color, shape = CircleShape)
                .size(30.wdp)
                .clip(CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = box),
                contentDescription = "flip icon",
                contentScale = ContentScale.Fit,
                modifier = Modifier.size(30.wdp)
            )
        }
        Spacer(modifier = Modifier.width(10.wdp))
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .weight(1f)
        ) {
            Text(
                text = type,
                style = MaterialTheme.MovemateTypes.bodyTextSmallNormal
            )
            Text(
                text = address,
                style = MaterialTheme.MovemateTypes.bodyTextMedium
            )
        }
        Spacer(modifier = Modifier.width(10.hdp))
        Column(
            verticalArrangement = Arrangement.SpaceBetween, modifier = Modifier
                .weight(1f)
        ) {
            Text(
                text = detail,
                style = MaterialTheme.MovemateTypes.bodyTextSmallNormal
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                if (trackingType == TrackingType.SENDER) {
                    Box(
                        modifier = Modifier
                            .size(8.wdp)
                            .clip(shape = CircleShape)
                            .background(color = Color(0xFF4aca2d)),
                    )
                    Spacer(modifier = Modifier.size(4.wdp))
                }

                Text(
                    text = duration,
                    style = MaterialTheme.MovemateTypes.bodyTextMedium
                )
            }
        }
    }
}


enum class TrackingType {
    SENDER, RECEIVER
}

@Composable
@Preview
fun TrackingSectionPreview() {
    MovemateTheme {
        TrackingDetailsSection()
    }
}