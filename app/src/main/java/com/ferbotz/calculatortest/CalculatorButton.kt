package com.ferbotz.calculatortest

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp


@Composable
fun CalculatorButton(modifier: Modifier, symbol: String, onClick: () -> Unit) {
    Box (
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clickable{
                onClick()
            }
    ){
        Text(
            text = symbol,
            fontSize = 36.sp,
            color = Color.White,
        )

    }
}
