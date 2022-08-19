package com.szn.kaam.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.szn.kaam.R
import com.szn.kaam.ui.navigation.CITATIONS
import com.szn.kaam.ui.widgets.Spinner
import com.szn.kaam.viewmodel.KaamViewModel

@Composable
fun CitsView(viewModel: KaamViewModel, navController: NavHostController, onSelected: (String) -> Unit) {
    var citations = viewModel.citations.value
    val persos = viewModel.persos
    var selected = remember { mutableStateOf("") }

    Column {

        Spinner(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.primaryVariant),
            dropDownModifier = Modifier.wrapContentSize(),
            items = persos,
            selectedItem = selected.value,
            onItemSelected = {
                Log.w("Cits", "onItemSelected $it")
                selected.value = it

//                viewModel.citations.value = citations.filter { it.infos.personnage == selected.value }.toMutableList()
//                citations = citations.filter { it.infos.personnage == selected.value }.toMutableList()
//                citations = viewModel.filter(selected.value)
                onSelected(it)
            },
            selectedItemFactory = { modifier, item ->
                Row(
                    modifier = modifier
                        .padding(8.dp)
                        .wrapContentSize()
                ) {
                    Text(item, color = MaterialTheme.colors.onPrimary)
                    Icon(
                        painter = painterResource(id = R.drawable.ic_outline_arrow_drop_down_circle_24),
                        contentDescription = "drop down arrow"
                    )
                }
            },
            dropdownItemFactory = { item, _ ->
                Text(text = item)
            }
        )
        Spacer(modifier = Modifier.fillMaxWidth().height(12.dp))
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier
                .testTag(CITATIONS)
                .background(MaterialTheme.colors.background)
        ) {
            itemsIndexed(items = citations) { index, item ->
                CitationCard(citation = item) {
                    Log.w("CitsView", "Selected $index ${item.citation}")
                    navController.currentBackStackEntry?.savedStateHandle?.apply {
                        set("citation", item)
                    }
                    // No Id, will take Parcelable in stack..
                    navController.navigate("detail/citation")
                }
            }
        }

    }
}

