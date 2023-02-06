package com.samdavid.photobank.data

import com.samdavid.photobank.models.ImagesResponseModel
import com.samdavid.photobank.models.VideosResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PixaBayApiService {
    //IMAGES
    @GET("api/")
    suspend fun fetchImages(): Response<ImagesResponseModel>
    @GET("api/")
    suspend fun filterImages(
        @Query("page") page: Int,
        @Query("per_page") pageSize: Int
    ): Response<ImagesResponseModel>

    //VIDEOS
    @GET("videos")
    fun fetchVideos(): Response<VideosResponseModel>
    @GET("videos")
    fun filterVideos(
        @Query("q") tag: String,
        @Query("category") videosCategory: String,
        @Query("video_type") imageType: String,
    ): Response<VideosResponseModel>
}