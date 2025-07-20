package com.movemate.calculate.presentation.components

import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.movemate.core.theme.base.MovemateColors
import com.movemate.core.theme.base.MovemateTheme
import com.movemate.core.theme.base.MovemateTypes
import com.movemate.core.theme.responsiveness.wdp

@Composable
fun CustomTextField(
    textValue: String,
    onValChange: (String) -> Unit,
    placeHolder: String,
    icon: @Composable () -> Unit?,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    trailingIcon: @Composable () -> Unit = {},
    backgroundColor: Color = Color(0xFFf7f7f7),
) {


    OutlinedTextField(
        prefix = {
            Row(Modifier.height(IntrinsicSize.Max)) {
                icon()
                Spacer(Modifier.width(5.wdp))
                VerticalDivider(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(1.wdp),
                    color = MaterialTheme.MovemateColors.textColor.copy(0.8f)

                )
                Spacer(Modifier.width(4.wdp))
            }
        },
        shape = RoundedCornerShape(10.wdp),
        value = textValue,
        onValueChange = onValChange,
        enabled = enabled,
        colors = OutlinedTextFieldDefaults.colors(
            disabledBorderColor = Color.Transparent,
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
            errorBorderColor = Color.Transparent,
            unfocusedContainerColor = backgroundColor,
            focusedContainerColor = backgroundColor,
            disabledContainerColor = backgroundColor,
            errorContainerColor = backgroundColor,
            focusedTextColor = MaterialTheme.MovemateColors.textColorHeader,
            unfocusedTextColor = MaterialTheme.MovemateColors.textColorHeader,
            errorTextColor = MaterialTheme.MovemateColors.textColorHeader,
            unfocusedPlaceholderColor = MaterialTheme.MovemateColors.textColor.copy(0.8f),
            focusedPlaceholderColor = MaterialTheme.MovemateColors.textColor.copy(0.8f),
        ),
        placeholder = {
            Text(
                placeHolder,
                style = MaterialTheme.MovemateTypes.bodyTextLarge.copy(
                    fontWeight = FontWeight.Normal,
                    color = MaterialTheme.MovemateColors.textColor.copy(0.8f)
                )
            )
        },
        modifier = modifier.fillMaxWidth(),
        trailingIcon = trailingIcon,
        textStyle = MaterialTheme.MovemateTypes.bodyTextLargeNormal.copy(
            fontWeight = FontWeight.Normal,
            color = MaterialTheme.MovemateColors.textColorHeader
        )
    )
}

@Preview
@Composable
private fun Preview() {
    MovemateTheme {
        CustomTextField("", {

        }, "placeholder", {})
    }
}
