package com.samdavid.photobank.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samdavid.photobank.data.repository.VideosRepository
import com.samdavid.photobank.models.Video
import com.samdavid.photobank.utils.Resource
import com.samdavid.photobank.utils.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VideosViewModel @Inject constructor(
    private val videosRepository: VideosRepository,
) : ViewModel() {
    private var _videosResponse =
        MutableStateFlow(value = Resource<List<Video>>(status = Status.LOADING, null, null))
    val imagesResponse = _videosResponse.asStateFlow()
    private var _videosFilterResponse =
        MutableStateFlow(value = Resource<List<Video>>(status = Status.LOADING, null, null))
    val imagesFilterResponse = _videosFilterResponse.asStateFlow()
    fun fetchVideos() = viewModelScope.launch(Dispatchers.IO) {
        val response = videosRepository.fetchVideos()
        if (response.isSuccessful) {
            response.body().let { imagesResponseModel ->
                _videosResponse.value = Resource.success(
                    data = imagesResponseModel?.hits,
                    message = "${imagesResponseModel?.totalHits} Videos Fetched"
                )
            }
        } else {
            _videosResponse
                .value = Resource.success(
                    data = null,
                    message = "An error occurred while fetching videos from server"
                )
        }
    }

    fun filterImages(category: String, tag: String, videoType: String) =
        viewModelScope.launch(Dispatchers.IO) {
            val response = videosRepository.filterVideos(category, tag, videoType)
            if (response.isSuccessful) {
                response.body().let { imagesResponseModel ->
                    _videosFilterResponse.value = Resource.success(
                        data = imagesResponseModel?.hits,
                        message = "${imagesResponseModel?.totalHits} Videos Fetched"
                    )
                }
            } else {
                _videosFilterResponse.value = Resource.success(
                    data = null,
                    message = "Failed, an error occurred while fetching videos from server"
                )
            }
        }
}