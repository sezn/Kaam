package com.szn.kaam.ui

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.szn.kaam.db.Quote
import com.szn.kaam.network.model.Citation
import com.szn.kaam.network.State
import com.szn.kaam.viewmodel.KaamViewModel
import kotlinx.coroutines.launch



@Composable
fun HomeView(navController: NavHostController, viewModel: KaamViewModel = hiltViewModel()) {
    val TAG = "Home"
    val scope = rememberCoroutineScope()
    val state by viewModel.state.collectAsState()
    var citations = viewModel.citations.value.toMutableList()
    Log.w(TAG, "init $state")

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .testTag(TAG)
            .padding(0.dp, 8.dp, 0.dp, 64.dp),
        color = MaterialTheme.colors.background
    ) {

        state.let { state ->
            Log.w(TAG, "State changed to $state")
            when(state) {
                /**
                 * If Loading, display CircularProgress
                 */
                State.START, State.LOADING -> {
                    LoadingView()
                }

                /**
                 * If Failed, Display a Message, and a Toast
                 */
                is State.FAILURE -> {
                    val message = state.message
                    // LaunchedEffect let us call Android.widget
                    LaunchedEffect(key1 = message) {
                        Toast.makeText(navController.context, message, Toast.LENGTH_SHORT).show()
                    }
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable {
                                scope.launch {
                                    viewModel.getAll()
                                }
                            },
                    ) {
                        Text(text = message, color = Color.Red,
                            fontWeight = FontWeight.Bold, fontSize = 28.sp,
                            modifier = Modifier
                                .align(Alignment.TopCenter)
                                .padding(horizontal = 0.dp, vertical = 64.dp)
                        )
                        CircularProgressIndicator(color = Color.Red)
                    }
                }

                /**
                 * Display a List of Citations
                 */
                State.SUCCESS -> {
                    Log.e(TAG, "State SUCCESS!!!")
                    CitsView(viewModel, navController){
                        filter(it, citations)
                    }
                }

            }
        }
    }

}

fun filter(selected: String, citations: MutableList<Quote>): MutableList<Quote> {
    Log.w("Filter", "filter $selected")
    return citations.filter { it.personnage == selected }.toMutableList()
}

