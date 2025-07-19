package com.movemate.home.presentation.components

import android.app.LauncherActivity
import androidx.compose.animation.core.EaseInElastic
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.movemate.core.theme.base.MovemateColors
import com.movemate.core.theme.base.MovemateTheme
import com.movemate.core.theme.base.MovemateTypes
import com.movemate.core.theme.base.defPadding
import com.movemate.core.theme.responsiveness.hdp
import com.movemate.core.theme.responsiveness.wdp
import com.movemate.core.theme.responsiveness.wsp
import com.movemate.home.R
import com.movemate.home.presentation.viewModels.mock.mockShipmentData


@Composable
fun LazyItemScope.ListItem(
    title: String,
    description: String,
) {
    Column(Modifier.animateItem(tween(durationMillis = 400, easing = EaseInElastic))) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            Box(
                Modifier
                    .background(MaterialTheme.MovemateColors.primary, CircleShape)
                    .padding(7.wdp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.icon_package),
                    modifier = Modifier.size(19.wdp),
                    tint = MaterialTheme.MovemateColors.cardColor,
                    contentDescription = null
                )
            }

            Spacer(Modifier.width(7.wdp))

            Column {

                Text(
                    title,
                    style = MaterialTheme.MovemateTypes.bodyTextMedium.copy(
                        color = MaterialTheme.MovemateColors.textColorHeader,
                        fontSize = 15.wsp

                    )
                )
                Spacer(Modifier.height(3.hdp))
                Text(
                    description,
                    style = MaterialTheme.MovemateTypes.bodyTextSmallNormal.copy(
                        color = MaterialTheme.MovemateColors.textColor,
                        fontSize = 13.wsp

                    )
                )

            }

        }

        HorizontalDivider(
            Modifier
                .fillMaxWidth()
                .padding(vertical = 10.hdp),
            color = MaterialTheme.MovemateColors.textColor.copy(0.2f)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ListItemPreview() {
    MovemateTheme {
        LazyColumn(Modifier.padding(horizontal = defPadding)) {
            items(mockShipmentData) {
                ListItem(
                    it.title,
                    "${it.trackingNumber} • ${it.route}"
                )
            }
        }
    }
}