package com.szn.kaam.ui.navigation

import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import com.szn.kaam.R

class NavBarItems(private val context: Context) {
    val BarItems = listOf(
        BarItem(
            title = context.getString(R.string.home),
            image = Icons.Filled.Home,
            route = NavRoutes.Home.route
        ),

    )
}