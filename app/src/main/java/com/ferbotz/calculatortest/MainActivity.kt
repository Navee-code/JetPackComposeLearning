package com.ferbotz.calculatortest


import android.os.Bundle
import android.util.Log
import androidx.compose.runtime.getValue

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold

import androidx.compose.material3.Surface
import androidx.compose.material3.Text

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ferbotz.calculatortest.database.AppDb
import com.ferbotz.calculatortest.ui.theme.CalculatorTestTheme

class MainActivity : ComponentActivity() {
    private val db: AppDb by lazy {
        Log.e("NoteID","DB init")
        AppDb.getDatabase(this)
    }
    private  val db1 =AppDb.getDatabase(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            CalculatorTestTheme {
                db
                db
                App()

            }
        }
    }
}
fun createDb(){
 //   db=AppDb.getDatabase(this)

}
@Composable
fun LazyColum() {
    LazyColumn {
        repeat(10000) { index ->
            item {
                Box( modifier = Modifier
                    .fillMaxSize(),
                    contentAlignment = Alignment.Center) {
                    Text(
                        text = "Value $index",
                        fontSize = 30.sp,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)

                    )
                }

            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomNavigationBar() {
    val navController = rememberNavController()
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            NavigationBar {
                BottomNavigationItem().bottomNavigationItems().forEachIndexed {index,navigationItem ->
                    NavigationBarItem(
                        selected = index == 0,
                        label = {
                            Text(navigationItem.label)
                        },
                        icon = {
                            Icon(
                                navigationItem.icon,
                                contentDescription = navigationItem.label
                            )
                        },
                        onClick = {
                            Log.e("PrintBottom ",index.toString())
                            var navigationSelectedItem = index
                            navController.navigate(navigationItem.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
         print(paddingValues)
        NavHost(navController = navController, startDestination = BottomNav.Home.route) {
            composable(BottomNav.Search.route) {
                CalculatorContent( )
            }
            composable(BottomNav.Home.route) {
                HomeBox()
            }
            composable(BottomNav.Profile.route) {
                LazyColum()

            }

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
                  //  CalculatorContent()
                }
            }
        )
    }
}

@Composable
fun HomeBox(){
    Box (
        modifier = Modifier
            .fillMaxSize(),
            contentAlignment = Alignment.Center
    ){
        Text("Home Page")
    }
}

@Composable
fun CalculatorContent() {
    val viewModel = viewModel<CalculatorViewModel>()
    val textState by viewModel.text.observeAsState()

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

@Composable
fun CalculatorTestTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        darkColorScheme(
            primary = Color.White,
            onPrimary = Color.Black,
            background = Color.Black,
            surface = Color.DarkGray,
            onSurface = Color.White
        )
    } else {
        lightColorScheme(
            primary = Color.Black,
            onPrimary = Color.White,
            background = Color.White,
            surface = Color.Gray,
            onSurface = Color.Black
        )
    }

//    MaterialTheme(
//        colorScheme = colorScheme,
//        typography = Typography,
//        content = content
//    )
}


//    MaterialTheme(
//        colorScheme = colorScheme,
//        typography = Typography(),
//        shapes = Shapes(),
//        content = content
//    )


//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    CalculatorTestTheme {
//        Greeting("Android")
//    }
//}