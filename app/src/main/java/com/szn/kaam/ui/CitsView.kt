package com.szn.kaam.ui

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.szn.kaam.model.Citation
import com.szn.kaam.ui.navigation.CITATIONS

@Composable
fun CitsView(citations: MutableList<Citation>, navController: NavHostController) {

    LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp),
        modifier = Modifier.testTag(CITATIONS)
    ) {
        itemsIndexed(items = citations) { index, item ->
            CitationCard(citation = item) {
                Log.w("CitsView", "Selected $index ${item.citation}")
                navController.currentBackStackEntry?.savedStateHandle?.apply {
                    set("citation", item)
                }
                // No Id, will take Parcelable in stack..
//                navController.navigate("$detail/citation")
            }
        }
    }

}

@Composable
fun CitationCard(citation: Citation, onClick: (Citation) -> Unit) {

    Card(
        backgroundColor = MaterialTheme.colors.background,
        elevation = 4.dp,
        modifier = Modifier
            .testTag("Citation")
            .fillMaxSize()
            .padding(8.dp)
            .clickable {
                onClick(citation)
            }

    ) {
        Column {
            Text(text = citation.citation, color = MaterialTheme.colors.onPrimary)
        }
    }
}
