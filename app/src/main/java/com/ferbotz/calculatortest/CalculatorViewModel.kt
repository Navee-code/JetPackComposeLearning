package com.ferbotz.calculatortest

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*

class CalculatorViewModel : ViewModel() {
    var state by mutableStateOf(CalculatorState())
    private val _text = MutableLiveData<String>()
    val text: LiveData<String> get() = _text



    fun onAction(action: CalculatorAction) {
        when (action) {
            CalculatorAction.Calculate -> performCalculation()
            CalculatorAction.Clear -> state = CalculatorState()
            CalculatorAction.Decimal -> enterDecimal()
            CalculatorAction.Delete -> performDelete()
            is CalculatorAction.Number -> enterNumber(action.number)
            is CalculatorAction.Operation -> enterOperation(action.action)
        }
    }

    private fun enterDecimal() {
        Log.e("Perform ","Decimal")
    }

    private fun enterOperation(operation: CalculatorAction){

    }

    private fun enterNumber(number:Int) {
        _text.value = if (_text.value != null) {
            _text.value + number.toString()
        } else {
            number.toString()
        }

    }

    private fun performDelete() {
        Log.e("Perform","Deletion")
    }

    private fun performCalculation() {}
}