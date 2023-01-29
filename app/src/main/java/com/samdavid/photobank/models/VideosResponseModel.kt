package com.samdavid.photobank.models

data class VideosResponseModel(
    val hits: List<Video>,
    val total: Int,
    val totalHits: Int
)