package com.samdavid.photobank.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.samdavid.photobank.theme.PhotoBankTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhotoListScreen() {
    Scaffold(
        bottomBar = {
            BottomAppBar()
        }
    ) { paddingValues ->

        LazyColumn(
            contentPadding = paddingValues,
            modifier = Modifier
                .fillMaxSize()
        ) {
            item {
                PhotoItemWidget(
                    imageUrl = "https://pixabay.com/get/g1bd50bc37a3b174f306db812b9a1651aaa67b3bc361d2a9935ba64db8f877bfadc5c1f257e6c775ebf46475b2657fc5887697dc1ac96d7d8253a1d88e876e2be_640.jpg",
                    tags = "Image, Internet",
                    views = "2K Views"
                )
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
            label = { Text(text = "Home") },
            selected = selectedItem,
            alwaysShowLabel = false,
            onClick = { selectedItem = true }
        )

        NavigationBarItem(
            icon = { Icons.Default.Menu },
            label = { Text(text = "Menu") },
            selected = selectedItem,
            alwaysShowLabel = false,
            onClick = { selectedItem = true }
        )

        NavigationBarItem(
            icon = { Icons.Default.Search },
            label = { Text(text = "Search") },
            selected = selectedItem,
            alwaysShowLabel = false,
            onClick = { selectedItem = true }
        )
    }
}

@Preview
@Composable
fun PhotosListview() {
    PhotoBankTheme {
        PhotoListScreen()
    }
}