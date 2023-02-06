package com.samdavid.photobank.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.samdavid.photobank.R
import com.samdavid.photobank.theme.PhotoBankTheme

@OptIn(ExperimentalCoilApi::class)
@Composable
fun PhotoItemWidget(
    imageUrl: String,
    tags: String,
    views: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp)
            .padding(vertical = 6.dp, horizontal = 16.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.elevatedCardElevation()

    ) {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (image, tagsText, viewsText) = createRefs()

            val imagePainter = rememberImagePainter(
                data = imageUrl,
                builder = {
                    placeholder(R.drawable.image_placeholder)
                }
            )

            Image(
                painter = imagePainter,
                contentDescription = "Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .constrainAs(image) {
                        top.linkTo(parent.top)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )

            Text(
                text = tags,
                style = TextStyle(color = Color.White, fontSize = 14.sp),
                modifier = Modifier
                    .constrainAs(tagsText) {
                        start.linkTo(parent.start, margin = 16.dp)
                        bottom.linkTo(parent.bottom, margin = 24.dp)
                    }
            )

            Text(
                text = views,
                style = TextStyle(color = Color.White, fontSize = 14.sp),
                modifier = Modifier
                    .constrainAs(viewsText) {
                        end.linkTo(parent.end, margin = 16.dp)
                        bottom.linkTo(tagsText.bottom)
                    }
            )
        }
    }
}
@Preview
@Composable
fun SpeakerComponentPreview() {
    PhotoBankTheme {
        PhotoItemWidget(
            imageUrl = "https://pixabay.com/get/g1bd50bc37a3b174f306db812b9a1651aaa67b3bc361d2a9935ba64db8f877bfadc5c1f257e6c775ebf46475b2657fc5887697dc1ac96d7d8253a1d88e876e2be_640.jpg",
            tags = "Bamboo, Forest, Brave",
            views = "2K Views"
        )
    }
}