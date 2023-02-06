package com.samdavid.photobank.models

data class PagingState(
    val isLoading: Boolean = false,
    val items: List<Image> = emptyList(),
    val error: String? = null,
    val endReached: Boolean = false,
    val page: Int = 1
)
