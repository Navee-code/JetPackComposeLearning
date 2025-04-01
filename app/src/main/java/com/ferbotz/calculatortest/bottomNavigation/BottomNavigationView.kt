package com.ferbotz.calculatortest.bottomNavigation

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ferbotz.calculatortest.homePage.CalculatorContent
import com.ferbotz.calculatortest.homePage.RemainderNoteList

@Composable
fun  BottomNavigationBar() {
    val navController = rememberNavController()
    var selectedIndex by remember { mutableIntStateOf(0) }
    Scaffold(
        bottomBar = {
            NavigationBar(
                modifier = Modifier
                    .padding(8.dp)
                    .clip(RoundedCornerShape(160.dp)),
                containerColor = Color.White
            ) {
                BottomNavigationItem().bottomNavigationItems().forEachIndexed { index, navigationItem ->

                    NavigationBarItem(
                        selected = selectedIndex == index,
                        label = { Text(navigationItem.label) },
                        icon = {
                            Icon(
                                navigationItem.icon,
                                contentDescription = navigationItem.label,
                            )
                        },
                        onClick = {
                            selectedIndex = index
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
        Box(modifier = Modifier.padding(paddingValues)) {
            NavHost(navController = navController, startDestination = BottomNav.Home.route) {
                composable(BottomNav.Search.route) { CalculatorContent() }
                composable(BottomNav.Home.route) { RemainderNoteList() }
                composable(BottomNav.Profile.route) {}
            }
        }
    }
}