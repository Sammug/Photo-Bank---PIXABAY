package com.samdavid.photobank.data.repository

import com.samdavid.photobank.data.PixaBayApiService
import javax.inject.Inject

class VideosRepository @Inject constructor(
    private val apiService: PixaBayApiService
    ) {

    fun fetchVideos() = apiService.fetchVideos()

   suspend fun filterVideos(page: Int,pageSize: Int) = apiService.filterVideos(page,pageSize)

}