package com.movemate.shipment.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.movemate.core.theme.base.MovemateColors
import com.movemate.core.theme.base.MovemateTheme
import com.movemate.core.theme.base.MovemateTypes
import com.movemate.core.theme.responsiveness.hdp
import com.movemate.core.theme.responsiveness.wdp
import com.movemate.core.theme.responsiveness.wsp
import com.movemate.core.utils.shadowCustom
import com.movemate.shipment.R
import com.movemate.shipment.presentation.viewModels.ShipmentStatus
import com.movemate.shipment.presentation.viewModels.listOfShipmentStatus

@Composable
fun LazyItemScope.ShipmentItem(item: ShipmentStatus) {
    Column(
        modifier = Modifier
            .animateItem()
            .shadowCustom()
            .clip(RoundedCornerShape(12.wdp))
            .background(color = MaterialTheme.MovemateColors.cardColor)
            .padding(12.wdp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clip(shape = RoundedCornerShape(20.wdp))
                .background(color = MaterialTheme.MovemateColors.bgColor)
                .padding(horizontal = 16.wdp, vertical = 4.hdp)
        ) {
            Icon(
                painter = painterResource(id = item.status.icon),
                tint = item.status.color,
                contentDescription = item.status.tag,
                modifier = Modifier.size(16.wdp)
            )
            Spacer(modifier = Modifier.size(4.wdp))
            Text(
                text = item.status.tag, color = item.status.color,
                style = MaterialTheme.MovemateTypes.bodyTextSmall.copy(fontWeight = FontWeight.Bold)
            )
        }
        Row {
            Column(modifier = Modifier.weight(1f)) {
                Spacer(modifier = Modifier.size(8.hdp))
                Text(
                    text = item.message,
                    fontSize = 18.wsp,
                    style = MaterialTheme.MovemateTypes.titleTextSmall,
                    color = MaterialTheme.MovemateColors.textColorHeader
                )
                Spacer(modifier = Modifier.size(6.hdp))
                Text(
                    text = item.details, fontSize = 12.wsp,
                    style = MaterialTheme.MovemateTypes.bodyTextSmall.copy(
                        color = MaterialTheme.MovemateColors.textColor
                    ), lineHeight = 18.sp
                )
                Spacer(modifier = Modifier.size(10.hdp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = item.amount,
                        style = MaterialTheme.MovemateTypes.bodyTextSmall.copy(
                            color = MaterialTheme.MovemateColors.primary
                        ),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Box(
                        modifier = Modifier
                            .padding(6.wdp)
                            .size(5.wdp)
                            .clip(shape = CircleShape)
                            .background(color = MaterialTheme.MovemateColors.textColor.copy(0.5f)),
                    )
                    Text(
                        text = item.date, fontSize = 12.sp,
                        style = MaterialTheme.MovemateTypes.titleTextSmall.copy(
                            fontWeight = FontWeight.Medium
                        ),
                        color = MaterialTheme
                            .MovemateColors.textColorHeader.copy(0.8f)
                    )
                }
            }
            Image(
                painter = painterResource(id = R.drawable.box),
                modifier = Modifier.size(80.wdp),
                contentDescription = "",
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun PreviewItem() {
    MovemateTheme {
        LazyColumn {
            item {
                ShipmentItem(listOfShipmentStatus.first())
            }
        }
    }
}