package com.example.unitconvertor

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class ConverterViewModel : ViewModel() {
    // Define mutable states for your screen
    var selectedOption1 = mutableStateOf("")
    var selectedOption2 = mutableStateOf("")
    var textValue = mutableStateOf("")
    var result = mutableStateOf("Result")
}
