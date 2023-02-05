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
    @GET("images")
    suspend fun filterImages(
        @Query("q") tag: String,
        @Query("category") imagesCategory: String,
        @Query("image_type") imageType: String,
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