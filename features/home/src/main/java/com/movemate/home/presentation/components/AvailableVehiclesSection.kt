package com.movemate.home.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.movemate.core.theme.base.MovemateTypes
import com.movemate.core.theme.base.defPadding
import com.movemate.core.theme.responsiveness.hdp
import com.movemate.core.theme.responsiveness.wdp
import com.movemate.core.theme.responsiveness.wsp
import com.movemate.home.R

@Composable
fun AvailableVehiclesSection(
    modifier: Modifier = Modifier,
    vehicles: List<Vehicle>,
    isVisible: Boolean,
) {


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


        LazyRow(
            Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(15.wdp)
        ) {
            items(items = vehicles) {
                FreightItem(
                    name = it.name,
                    status = it.status,
                    image = it.image,
                    isListVisible = isVisible,
                )
            }
        }
    }
}


data class Vehicle(
    val name: String,
    val status: String,
    val image: Int
)

val dummyVehiclesList = listOf(
    Vehicle("Ocean freight", "International", R.drawable.ship),
    Vehicle("Cargo freight", "Reliable", R.drawable.truck),
    Vehicle("Air freight", "International", R.drawable.plane),
    Vehicle("Rail freight", "Reliable", R.drawable.train),
)