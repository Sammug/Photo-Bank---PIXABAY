package com.samdavid.photobank.ui

import android.annotation.SuppressLint
import android.provider.MediaStore
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.samdavid.photobank.R
import com.samdavid.photobank.models.Image
import com.samdavid.photobank.models.PagingState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhotoListScreen(
    images: List<Image>
) {
    Scaffold(
        bottomBar = {
            BottomAppBar(
                modifier = Modifier.heightIn(42.dp,48.dp)
            )
        },
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.surfaceVariant
    ) {

        LazyColumn(
            contentPadding = PaddingValues(vertical = 8.dp),
            modifier = Modifier
                .fillMaxSize()
        ) {
            item {
                images.forEach { image ->
                    PhotoItemWidget(
                        imageUrl = image.largeImageURL,
                        tags = image.tags,
                        views = buildString {
                            append(image.views)
                            append(" Views")
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun BottomAppBar(
    modifier: Modifier = Modifier
) {
    var selectedItem by remember {
        mutableStateOf(false)
    }
    BottomAppBar(tonalElevation = 0.dp) {

        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_photo_filter),
                    contentDescription = "settings icon"
                )
            },
            label = { Text(text = "Images") },
            alwaysShowLabel = false,
            selected = selectedItem,
            onClick = { selectedItem = true }
        )

        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_videos),
                    contentDescription = "settings icon"
                )
            },
            label = { Text(text = "Videos") },
            alwaysShowLabel = false,
            selected = selectedItem,
            onClick = { selectedItem = true }
        )

        NavigationBarItem(
            icon = {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_settings),
                    contentDescription = "settings icon"
                )
            },
            label = { Text(text = "Settings") },
            alwaysShowLabel = false,
            selected = selectedItem,
            onClick = { selectedItem = true }
        )
    }
}