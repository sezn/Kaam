package com.szn.kaam.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.RoundedCornersTransformation
import com.szn.kaam.R
import com.szn.kaam.Utils
import com.szn.kaam.model.Citation
import com.szn.kaam.ui.navigation.CITATIONS

@Composable
fun CitsView(citations: MutableList<Citation>, navController: NavHostController) {

    LazyColumn(verticalArrangement = Arrangement.spacedBy(4.dp),
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

