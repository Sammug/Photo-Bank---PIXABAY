package com.samdavid.photobank.ui

import androidx.lifecycle.ViewModel
import com.samdavid.photobank.data.repository.ImagesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ImagesViewModel @Inject constructor(
    private val imagesRepository: ImagesRepository
) : ViewModel() {

}