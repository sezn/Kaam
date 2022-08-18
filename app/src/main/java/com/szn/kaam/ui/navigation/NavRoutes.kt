package com.szn.kaam.ui.navigation

sealed class NavRoutes(val route: String) {
    object Home : NavRoutes(HOME)
    object Detail : NavRoutes("$DETAIL/{id}")
}
const val HOME = "home"
const val DETAIL = "detail"