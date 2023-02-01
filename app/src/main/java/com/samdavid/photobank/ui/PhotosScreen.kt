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
import androidx.compose.ui.unit.dp

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
            item { }
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
            alwaysShowLabel = selectedItem,
            onClick = { selectedItem = true }
        )

        NavigationBarItem(
            icon = { Icons.Default.Menu },
            label = { Text(text = "Menu") },
            selected = selectedItem,
            alwaysShowLabel = selectedItem,
            onClick = { selectedItem = true }
        )

        NavigationBarItem(
            icon = { Icons.Default.Search },
            label = { Text(text = "Search") },
            selected = selectedItem,
            alwaysShowLabel = selectedItem,
            onClick = { selectedItem = true }
        )
    }
}