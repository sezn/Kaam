package com.szn.kaam.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val context = LocalContext.current
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    BottomNavigation {
        NavBarItems(context).BarItems.forEach { navItem ->

            BottomNavigationItem(
                selected = currentRoute == navItem.route,
                unselectedContentColor = Color.White,
                selectedContentColor = MaterialTheme.colors.primary,
                modifier = Modifier
                    .background(MaterialTheme.colors.background)
                    .testTag(navItem.route),
                onClick = {
                    if(currentRoute.equals(navItem.route)){
                        // Reselect, let recompose
                        navController.popBackStack()
                    }

                    navController.navigate(navItem.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            if(navItem.route != NavRoutes.Home.route)
                                saveState = true
                       }
                        // Avoid multiple copies of the same destination when reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                },
 
                icon = {
                    Icon(imageVector = navItem.image, 
                           contentDescription = navItem.title)
                },
                label = {
                    Text(text = navItem.title)
                },
            )
        }
    }

}
