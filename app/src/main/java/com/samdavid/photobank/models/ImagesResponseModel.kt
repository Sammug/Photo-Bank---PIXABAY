package com.samdavid.photobank.models

data class ImagesResponseModel(
    val hits: List<Image>,
    val total: Int,
    val totalHits: Int
)