package com.example.finalproject.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.finalproject.screens.ui.theme.FinalProjectTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImagesGrid() {
	val randomWidths = List(30) { (50..1000).random() }
	val randomHeights = List(30) { (50..1000).random() }
	val randomImages = randomWidths.zip(randomHeights).map {
		"https://picsum.photos/${it.first}/${it.second}"
	}

	LazyVerticalStaggeredGrid(
		modifier = Modifier.fillMaxSize(),
		columns = StaggeredGridCells.Fixed(2),
		horizontalArrangement = Arrangement.spacedBy(8.dp),
		verticalArrangement = Arrangement.spacedBy(8.dp),
		contentPadding = PaddingValues(8.dp),
		content = {
			items(randomImages.size) { index ->
				Image(
					painter = rememberAsyncImagePainter(randomImages[index]),
					contentDescription = null,
					modifier = Modifier
						.fillMaxWidth()
						.heightIn(max = randomHeights[index].dp)
						.clip(
							RoundedCornerShape(16.dp)
						),
					contentScale = ContentScale.Crop
				)
			}
		},
	)
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
	FinalProjectTheme {
		ImagesGrid()
	}
}

