package com.szn.kaam.ui

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.szn.kaam.R
import com.szn.kaam.db.Quote
import com.szn.kaam.ui.navigation.BottomNavigationBar
import com.szn.kaam.ui.navigation.NavRoutes
import com.szn.kaam.ui.theme.KaamTheme

@Composable
fun Skeleton(lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current) {
    val navController = rememberNavController()
    val conf = LocalConfiguration.current
    var title = mutableStateOf(stringResource(id = R.string.app_name))

    var (canPop, setCanPop) = remember { mutableStateOf(false) }
    navController.addOnDestinationChangedListener { controller, _, _ ->
        setCanPop(controller.previousBackStackEntry != null)
    }


    KaamTheme {
        /**
         * Scaffold implements the basic material design visual layout structure.
         * It's a Skeleton where you can put TopBar, Content, BottomBar, SnackBar etc..
         */
        Scaffold(modifier = Modifier.background(Color.Black),
            topBar = {
                if(conf.orientation == Configuration.ORIENTATION_PORTRAIT)
                    TopAppBar(
                        title = { Text(title.value, color = MaterialTheme.colors.onPrimary) },
                        backgroundColor = MaterialTheme.colors.background,
                        navigationIcon = {
                            if (canPop) {
                                IconButton(onClick = {
                                    navController.popBackStack()
                                }) {
                                    Icon(imageVector = Icons.Filled.ArrowBack, null,
                                        tint = MaterialTheme.colors.onPrimary)
                                }
                            }
                        }
                    )
            },    // Just to show it can be done
            content = { NavigationHost(navController = navController, lifecycleOwner) },
            bottomBar = {
                if(conf.orientation == Configuration.ORIENTATION_PORTRAIT)
                    BottomNavigationBar(navController = navController)
            }
        )

    }

}


@Composable
fun NavigationHost(
    navController: NavHostController,
    lifecycleOwner: LifecycleOwner) {

    NavHost(
        navController = navController,
        startDestination = NavRoutes.Home.route,
        modifier = Modifier.padding(0.dp)
    ) {

        composable(
            NavRoutes.Home.route,
            arguments = listOf()) {
            HomeView(navController)
        }

        composable(NavRoutes.Detail.route,
            arguments = listOf(navArgument("citation") { })) { backStackEntry ->
            val cit = navController.previousBackStackEntry?.savedStateHandle?.get<Quote>("citation")
            Log.w("NavHost", "navigate to detail ${cit?.citation}")
            cit?.let {
                CitationView(it)
            }
        }

    }
}

