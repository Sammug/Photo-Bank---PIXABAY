package com.samdavid.photobank.data

import com.samdavid.photobank.models.ImagesResponseModel
import com.samdavid.photobank.models.VideosResponseModel
import retrofit2.Response
import retrofit2.http.GET

interface PixaBayApiService {
    @GET("images")
    fun fetchImages(): Response<ImagesResponseModel>
    @GET("videos")
    fun fetchVideos(): Response<VideosResponseModel>
}