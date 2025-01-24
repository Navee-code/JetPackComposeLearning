package com.ferbotz.calculatortest

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign

@Composable
fun Calculator(
    state: CalculatorState,
    modifier: Modifier = Modifier
){
    Box(modifier = modifier){
        Column (
            modifier = Modifier
                .fillMaxHeight()
                .align(Alignment.BottomCenter)
        ){
            Text(
                text = state.number1 + state.number2,
                textAlign = TextAlign.End,
                modifier = Modifier.fillMaxHeight()
            )

        }

    }


}