package com.movemate.home.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.movemate.core.theme.base.MovemateColors
import com.movemate.core.theme.base.MovemateTheme
import com.movemate.core.theme.base.defPadding
import com.movemate.core.theme.responsiveness.wdp
import com.movemate.home.presentation.viewModels.mock.mockShipmentData


@Composable
fun SearchListItems(
    modifier: Modifier = Modifier,
    query: String = ""
) {

    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.MovemateColors.cardColor
        ),
        shape = RoundedCornerShape(15.wdp),
        modifier = modifier.fillMaxWidth()
    ) {

        LazyColumn(Modifier.padding(defPadding).fillMaxWidth()) {
            items(
                mockShipmentData
                    .take(5)
                    .filter {
                        it.trackingNumber
                            .contains(query, true)
                    },
                key = { it.id }) {
                ListItem(
                    it.title,
                    "${it.trackingNumber} • ${it.route}"
                )
            }
        }

    }

}


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun ListItemPreview() {
    MovemateTheme {
        SearchListItems(Modifier.padding(defPadding))
    }
}

