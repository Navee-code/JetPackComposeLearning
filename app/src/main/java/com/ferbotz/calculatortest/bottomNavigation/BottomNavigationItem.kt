package com.ferbotz.calculatortest.bottomNavigation

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavigationItem(
    val label : String = "",
    val icon : ImageVector = Icons.Filled.Home,
    val route : String = ""
) {

    var lambda = {a: Int , b: Int -> a * b }

    val lambada1 ={ a:String, _:Int -> Log.e("Message",a)}

    val home:String ="home_route"

    fun bottomNavigationItems() : List<BottomNavigationItem> {
        return listOf(
            BottomNavigationItem(
                label = "Home",
                icon = Icons.Filled.Home,
                route = "home_route"
            ),
            BottomNavigationItem(
                label = "Search",
                icon = Icons.Filled.Search,
                route = "search_route"
            ),
            BottomNavigationItem(
                label = "Profile",
                icon = Icons.Filled.AccountCircle,
                route = "profile_route"
            ),
        )
    }

    fun kotlinFunction(num:String):String{
        lambda(2,3)
        lambada1("Eroor",2)
       HighOrderKotlin {

       }
        return ""
    }


    fun HighOrderKotlin(callBack: (String) -> Unit){
        callBack("CallBAck")
    }

}
