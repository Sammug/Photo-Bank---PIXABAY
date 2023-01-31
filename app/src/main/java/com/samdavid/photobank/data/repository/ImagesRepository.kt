package com.samdavid.photobank.data.repository

import com.samdavid.photobank.data.PixaBayApiService
import com.samdavid.photobank.models.Image
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ImagesRepository @Inject constructor(
    private val apiService: PixaBayApiService
    ) {
    fun fetchImages() = flow<List<Image>> {
        apiService.fetchImages()
    }
}