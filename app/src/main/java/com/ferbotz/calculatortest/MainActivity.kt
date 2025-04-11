package com.ferbotz.calculatortest


import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.compose.runtime.getValue

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button


import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold

import androidx.compose.material3.Surface
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable

import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier

import androidx.lifecycle.lifecycleScope

import com.ferbotz.calculatortest.bottomNavigation.BottomNavigationBar

import com.ferbotz.calculatortest.ui.theme.CalculatorTestTheme
import kotlinx.coroutines.*
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val counter by remember { mutableStateOf(0) }
            CalculatorTestTheme {
               val deffered = lifecycleScope.async(Dispatchers.Main) {
                   delay(5000)
                   "fd"
               }


                lifecycleScope.launch {
                    deffered.await()

                    Toast.makeText(this@MainActivity,"deffered wait",Toast.LENGTH_SHORT).show()
                }
                lifecycleScope.launchWhenCreated {  }
                MyComponent()

            }
        }
    }
}

@Composable
fun MyComponent() {
    var count by remember { mutableIntStateOf(3) } // ← persists across recompositions
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = { count++ }) {
            Text("Click me: $count")
        }
    }
}
@Composable
fun MyComponent2() {
    var count = 0// ← persists across recompositions

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Button(onClick = { count++ }) {
            Text("Click me per: $count")
        }
    }
}

@Composable
fun App() {
    CalculatorTestTheme {
        Scaffold(
            topBar = {
            },
            bottomBar = {
                NavigationBar {
                    BottomNavigationBar()
                }
            },
            content = { paddingValues ->
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    color = MaterialTheme.colorScheme.background
                ) {
                }
            }
        )
    }
}






