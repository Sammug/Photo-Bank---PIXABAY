package com.samdavid.photobank.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.samdavid.photobank.models.Image
import com.samdavid.photobank.theme.PhotoBankTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhotoListScreen(
    images: List<Image>
) {
    Scaffold(
        bottomBar = {
            BottomAppBar()
        }
    ) { paddingValue ->

        LazyColumn(
            contentPadding = PaddingValues(vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValue)
        ) {
            item {
                images.forEach {image ->
                    PhotoItemWidget(
                        imageUrl = image.largeImageURL,
                        tags = image.tags,
                        views = buildString {
                            append(image.views)
                            append("Views")
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun BottomAppBar() {
    var selectedItem by remember {
        mutableStateOf(false)
    }
    BottomAppBar(tonalElevation = 0.dp) {

        NavigationBarItem(
            icon = { Icons.Default.Home },
            label = { Text(text = "Images") },
            selected = selectedItem,
            onClick = { selectedItem = true }
        )

        NavigationBarItem(
            icon = { Icons.Default.Menu },
            label = { Text(text = "Videos") },
            selected = selectedItem,
            onClick = { selectedItem = true }
        )

        NavigationBarItem(
            icon = { Icons.Default.Settings },
            label = { Text(text = "Settings") },
            selected = selectedItem,
            onClick = { selectedItem = true }
        )
    }
}