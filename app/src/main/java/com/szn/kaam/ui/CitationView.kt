package com.szn.kaam.ui

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.szn.kaam.network.model.Citation

@Composable
fun CitationView(cit: Citation) {

    Text(text = cit.citation, color = Color.White)
}