package com.ferbotz.calculatortest

import android.net.http.HttpException
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.ferbotz.calculatortest.database.Remainders
import com.ferbotz.calculatortest.helper.log
import com.ferbotz.calculatortest.retrofit.Client
import com.ferbotz.calculatortest.retrofit.Gallery
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onEmpty
import kotlinx.coroutines.launch

class CalculatorViewModel : ViewModel() {
    var state by mutableStateOf(CalculatorState())
    private val _text = MutableLiveData<String>()
    val text: LiveData<String> get() = _text

    private val homeText = MutableLiveData("Home Page")
    val data:LiveData<String> get() = homeText

    private val wallet = MutableLiveData<String>()
    val _wallet:LiveData<String> get() = wallet

    private val _notes = MutableStateFlow<List<Remainders>>(emptyList())
    val notes: StateFlow<List<Remainders>> = _notes.asStateFlow()

    private  val photos = MutableStateFlow<List<Gallery>>(emptyList())
    val _photos: StateFlow<List<Gallery>> get() = photos

    private val snackBar = MutableSharedFlow<String>()
    val _snackBar:SharedFlow<String> get() = snackBar

    init {
        fetchNotes()
    }
    fun fetchPhotos() {
        wallet.value = "fnjn"
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = Client.instance.getPhotos().execute()
                if (response.isSuccessful) {
                    val photo = response.body()
                    photo?.let {
                        photos.emit(photo)
                        "Updating Composable Caluculater".log("ViewMODelSCope")
                    }
                   // _wallet.value = photos
                }
            } catch (e: Exception) {
                Log.e("PhotoViewModel", "HTTP Error: ${"fd"}")
            } catch (e: Exception) {
                Log.e("PhotoViewModel", "Error: ${e.localizedMessage}")
            }
        }
    }

    private fun fetchNotes() {
        viewModelScope.launch(Dispatchers.IO) {
          //  val data = db.noteDao().getAllNotes()
          //  _notes.value = data
        }
    }

    fun updateValue(value:String){
        homeText.value = value
    }


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