package com.samdavid.photobank.data.paging

interface Paginator<Key,Item> {
    suspend fun loadNextItems()
    fun reset()
}