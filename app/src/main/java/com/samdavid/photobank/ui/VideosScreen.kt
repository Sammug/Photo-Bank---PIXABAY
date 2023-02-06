package com.samdavid.photobank.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.samdavid.photobank.R
import com.samdavid.photobank.models.Image
import com.samdavid.photobank.models.Video

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun VideoListScreen(
    videos: List<Video>
) {
    LazyColumn(
        contentPadding = PaddingValues(vertical = 8.dp),
        modifier = Modifier
            .fillMaxSize()
    ) {
        item {
            videos.forEach {

                VideoComponent(
                    url = it.videos.medium.url,
                    tags = it.tags,
                    views = buildString {
                        append(it.views)
                        append(" Views")
                    }
                )
            }
        }
    }
}