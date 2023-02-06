package com.samdavid.photobank.ui

import android.view.LayoutInflater
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.media3.ui.PlayerView.SHOW_BUFFERING_WHEN_PLAYING
import coil.compose.rememberImagePainter
import com.samdavid.photobank.R
@androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
@Composable
fun VideoComponent(
    url: String,
    tags: String,
    views: String
) {
    //exoplayer
    val context = LocalContext.current
    val exoPlayer = remember(context) { ExoPlayer.Builder(context).build() }
    exoPlayer.setMediaItem(MediaItem.fromUri(url))
    exoPlayer.prepare()
    exoPlayer.playWhenReady = true


    //player view
    val playerView = remember {
        val layout = LayoutInflater.from(context).inflate(R.layout.video_player,null, false)
        val playerView = layout.findViewById(R.id.playerView) as PlayerView
        playerView.apply {
            player = exoPlayer
            showController()
            setShowBuffering(SHOW_BUFFERING_WHEN_PLAYING)
        }
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(380.dp)
            .padding(vertical = 6.dp, horizontal = 12.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (video, tagsText, viewsText, thumbNail) = createRefs()
            
            AndroidView(
                factory = { playerView },
                modifier = Modifier
                    .height(280.dp)
                    .background(color = Color.Black)
                    .constrainAs(video) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )

            VideoThumbnail(
                url = url,
                modifier = Modifier.constrainAs(thumbNail) {
                    top.linkTo(video.top)
                    bottom.linkTo(video.bottom)
                    start.linkTo(video.start)
                    end.linkTo(video.end)
                }
            )

            Text(
                text = tags,
                style = TextStyle(color = Color.White, fontSize = 14.sp),
                modifier = Modifier
                    .constrainAs(tagsText) {
                        start.linkTo(video.start, margin = 16.dp)
                        top.linkTo(video.bottom, margin = 16.dp)
                    })

            Text(
                text = views,
                style = TextStyle(color = Color.White, fontSize = 14.sp),
                modifier = Modifier
                    .constrainAs(viewsText) {
                        start.linkTo(video.start, margin = 16.dp)
                        top.linkTo(tagsText.bottom, margin = 8.dp)
                    })

        }
    }
}

@Composable
fun VideoThumbnail(url: String, modifier: Modifier) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()){
        val imagePainter = rememberImagePainter(
            data = url
        )

        Image(
            painter = imagePainter,
            contentDescription = "Thumbnail",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )
    }
}