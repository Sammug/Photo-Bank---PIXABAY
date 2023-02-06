package com.samdavid.photobank

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.samdavid.photobank.models.Image
import com.samdavid.photobank.theme.PhotoBankTheme
import com.samdavid.photobank.ui.viewmodels.ImagesViewModel
import com.samdavid.photobank.ui.PhotoListScreen
import com.samdavid.photobank.ui.VideoComponent
import com.samdavid.photobank.ui.VideoListScreen
import com.samdavid.photobank.ui.viewmodels.VideosViewModel
import com.samdavid.photobank.utils.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PhotoBankTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val imagesViewModel: ImagesViewModel = hiltViewModel()
                    val videosViewModel: VideosViewModel = hiltViewModel()
                    val state = videosViewModel.videosFilterResponse.collectAsState()

                    state.value.let { resource ->
                        when(resource.status){
                            Status.SUCCESS -> {
                                resource.data?.let { videos ->
                                    VideoListScreen(videos = videos)
                                }
                            }
                            Status.ERROR -> {

                            }
                            Status.LOADING -> {

                            }
                        }
                    }

                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PhotoBankTheme {
        val images = emptyList<Image>()
        PhotoListScreen(images)
    }
}