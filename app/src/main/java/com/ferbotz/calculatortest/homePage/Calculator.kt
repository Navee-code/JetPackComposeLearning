package com.ferbotz.calculatortest.homePage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ferbotz.calculatortest.CalculatorAction
import com.ferbotz.calculatortest.CalculatorButton
import com.ferbotz.calculatortest.CalculatorViewModel
import com.ferbotz.calculatortest.helper.log
import com.ferbotz.calculatortest.retrofit.Gallery
import kotlinx.coroutines.flow.collectLatest

@Composable
fun CalculatorContent() {
    val viewModel = viewModel<CalculatorViewModel>()
    val textState by viewModel.text.observeAsState()

    "Updating Composable Caluculater".log("ViewMODelSCope")


    val buttonModifier = Modifier
        .size(80.dp)
        .clip(CircleShape)
        .background(Color.Gray)
    val SymbolbuttonModifier = Modifier
        .size(80.dp)
        .clip(CircleShape)
        .background(Color(0xFFFFA500))
    val bigButton = Modifier
        .size(width = 170.dp, height = 80.dp)
        .clip(CircleShape)
        .background(Color.Gray)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp),
        verticalArrangement = Arrangement.Bottom
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            horizontalArrangement = Arrangement.End,

            ) {
            Text(
                text = textState ?: "0",
                fontSize = 40.sp,
                color = Color.White,
                lineHeight = 35.sp,
            )

        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,

            ) {
            CalculatorButton(modifier = bigButton, symbol = "AC") {
                viewModel.onAction(CalculatorAction.Delete)
            }
            CalculatorButton(modifier = buttonModifier, symbol = "Del") {
                viewModel.onAction(CalculatorAction.Clear)

            }
            CalculatorButton(modifier = SymbolbuttonModifier, symbol = "/") {

            }

        }
        Spacer(modifier = Modifier.height(14.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,

            ) {
            CalculatorButton(modifier = buttonModifier, symbol = "7") {
                viewModel.onAction(CalculatorAction.Number(7))
            }
            CalculatorButton(modifier = buttonModifier, symbol = "8") {
                viewModel.onAction(CalculatorAction.Number(8))
            }
            CalculatorButton(modifier = buttonModifier, symbol = "9") {
                viewModel.onAction(CalculatorAction.Number(9))
            }
            CalculatorButton(modifier = SymbolbuttonModifier, symbol = "*") {

            }
        }
        Spacer(modifier = Modifier.height(14.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,

            ) {
            CalculatorButton(modifier = buttonModifier, symbol = "4") {
                viewModel.onAction(CalculatorAction.Number(4))

            }
            CalculatorButton(modifier = buttonModifier, symbol = "5") {
                viewModel.onAction(CalculatorAction.Number(5))

            }
            CalculatorButton(modifier = buttonModifier, symbol = "6") {
                viewModel.onAction(CalculatorAction.Number(6))

            }
            CalculatorButton(modifier = SymbolbuttonModifier, symbol = "-") {

            }
        }
        Spacer(modifier = Modifier.height(14.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,

            ) {
            CalculatorButton(modifier = buttonModifier, symbol = "1") {
                viewModel.onAction(CalculatorAction.Number(1))

            }
            CalculatorButton(modifier = buttonModifier, symbol = "2") {
                viewModel.onAction(CalculatorAction.Number(2))

            }
            CalculatorButton(modifier = buttonModifier, symbol = "3") {
                viewModel.onAction(CalculatorAction.Number(3))

            }
            CalculatorButton(modifier = SymbolbuttonModifier, symbol = "+") {

            }
        }
        Spacer(modifier = Modifier.height(14.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,

            ) {
            CalculatorButton(modifier = bigButton, symbol = "0") {
                viewModel.onAction(CalculatorAction.Number(0))


            }
            CalculatorButton(modifier = buttonModifier, symbol = ".") {

            }
            CalculatorButton(modifier = SymbolbuttonModifier, symbol = "=") {

            }
        }
    }
}