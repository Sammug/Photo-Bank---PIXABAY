package com.samdavid.photobank.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samdavid.photobank.data.repository.ImagesRepository
import com.samdavid.photobank.models.Image
import com.samdavid.photobank.utils.Resource
import com.samdavid.photobank.utils.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImagesViewModel @Inject constructor(
    private val imagesRepository: ImagesRepository,
) : ViewModel() {
    private var _imagesResponse = MutableStateFlow(value = Resource<List<Image>>(status = Status.LOADING,null,null))
    val imagesResponse  = _imagesResponse.asStateFlow()
    private var _imagesFilterResponse = MutableStateFlow(value = Resource<List<Image>>(status = Status.LOADING,null,null))
    val imagesFilterResponse  = _imagesFilterResponse.asStateFlow()
    fun fetchImages() = viewModelScope.launch(Dispatchers.IO){
        val response = imagesRepository.fetchImages()
        if (response.isSuccessful){
            response.body().let { imagesResponseModel ->
                _imagesResponse.value = Resource.success(data = imagesResponseModel?.hits, message = "${imagesResponseModel?.totalHits} Fetched")
            }
        }else {
            _imagesResponse.value = Resource.success(data = null, message = "An error occurred while fetching images from server")
        }
    }
    fun filterImages(category: String, tag: String, imageType: String) = viewModelScope.launch(Dispatchers.IO){
        val response = imagesRepository.filterImages(category, tag, imageType)
        if (response.isSuccessful){
            response.body().let { imagesResponseModel ->
                _imagesFilterResponse.value = Resource.success(data = imagesResponseModel?.hits, message = "${imagesResponseModel?.totalHits} Fetched")
            }
        }else {
            _imagesFilterResponse.value = Resource.success(data = null, message = "Failed, an error occurred while fetching images from server")
        }
    }
}