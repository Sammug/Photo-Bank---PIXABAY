package com.samdavid.photobank.data.repository

import com.samdavid.photobank.data.PixaBayApiService
import javax.inject.Inject

class ImagesRepository @Inject constructor(
    private val apiService: PixaBayApiService
    ) {
    fun fetchImages() = apiService.fetchImages()

    fun filterImages(category: String, tag:String, imageType: String) = apiService.filterImages(category,tag,imageType)

}