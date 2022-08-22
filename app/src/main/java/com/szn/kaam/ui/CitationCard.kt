package com.szn.kaam.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.szn.kaam.R
import com.szn.kaam.db.MockCitation
import com.szn.kaam.db.Quote


@Composable
fun CitationCard(citation: Quote, onClick: (Quote) -> Unit) {
    val context = LocalContext.current
    val img = citation.getPicturePath()
    Card(
        backgroundColor = MaterialTheme.colors.primarySurface,
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .testTag("Citation")
            .padding(8.dp, 0.dp, 8.dp, 0.dp)
            .clickable {
                onClick(citation)
            }
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            Text(
                text = citation.citation,
                style = MaterialTheme.typography.subtitle1,
                color = MaterialTheme.colors.onPrimary,
                modifier = Modifier.weight(0.7f)
            )

            Column(modifier = Modifier
                .weight(0.3f)
                .fillMaxHeight() ,
                verticalArrangement = Arrangement.Center
            ) {

                Image(
                    painter = rememberAsyncImagePainter(
                        remember(img) {
                            ImageRequest.Builder(context)
                                .data(img)
                                .error(R.mipmap.ic_launcher_round)
                                .placeholder(R.mipmap.ic_launcher_round)
                                .build()
                        }
                    ),
                    contentDescription = citation.citation,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .align(CenterHorizontally)
                )
                Text(text = citation.personnage.toString(),
                    color = MaterialTheme.colors.onPrimary,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}


@Preview
@Composable
fun CitationPreview() {
    CitationCard(MockCitation) {}
}