package com.samdavid.photobank.data.repository

import com.samdavid.photobank.data.PixaBayApiService
import javax.inject.Inject

class VideosRepository @Inject constructor(
    private val apiService: PixaBayApiService
    ) {

    fun fetchVideos() = apiService.fetchVideos()

    fun filterVideos(category: String, tag:String, imageType: String) = apiService.filterVideos(category,tag,imageType)

}