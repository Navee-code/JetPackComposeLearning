package com.ferbotz.calculatortest

sealed class BottomNav(val route : String) {
        object Home : BottomNav("home_route")
        object Search : BottomNav("search_route")
        object Profile : BottomNav("profile_route")
}