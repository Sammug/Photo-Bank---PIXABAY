package com.samdavid.photobank.ui.viewmodels

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
    val videosFilterResponse = _videosFilterResponse.asStateFlow()

    init {
        filterImages(page = 1,3)
    }

    private fun filterImages(page: Int, pageSize: Int) =
        viewModelScope.launch(Dispatchers.IO) {
            val response = videosRepository.filterVideos(page, pageSize)
            if (response.isSuccessful) {
                response.body().let { responseModel ->
                    _videosFilterResponse.value = Resource.success(
                        data = responseModel?.hits,
                        message = "${responseModel?.totalHits} Videos Fetched"
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