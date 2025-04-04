package com.ferbotz.calculatortest

import com.ferbotz.calculatortest.retrofit.Gallery

sealed class CalculatorOperation(val symbol: String) {
    object Add : CalculatorOperation("+")
    object Subtract : CalculatorOperation("-")
    object Divide : CalculatorOperation("/")
    object Multiply : CalculatorOperation("*")
}

sealed class NetworkAPi(val gallery: String){
    data class Success(val data: Gallery) : NetworkAPi("404")
    object Failure : NetworkAPi("505")
}