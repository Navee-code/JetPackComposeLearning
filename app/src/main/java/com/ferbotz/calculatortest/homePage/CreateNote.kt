package com.ferbotz.calculatortest.homePage

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.ferbotz.calculatortest.AppApplication

@Composable
fun RemainderNoteList() {
    var showDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current.applicationContext as AppApplication
}