package com.samdavid.photobank

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.samdavid.photobank.models.Image
import com.samdavid.photobank.theme.PhotoBankTheme
import com.samdavid.photobank.ui.ImagesViewModel
import com.samdavid.photobank.ui.PhotoListScreen
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
                    val state = imagesViewModel.imagesResponse.collectAsState()

                    state.value.let { resource ->
                        when(resource.status){
                            Status.SUCCESS -> {
                                resource.data?.let { images ->
                                    PhotoListScreen(images = images)
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