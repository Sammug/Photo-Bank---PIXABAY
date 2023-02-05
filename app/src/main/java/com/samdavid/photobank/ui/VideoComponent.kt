package com.samdavid.photobank.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
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
fun VideoComponent(
    url: String,
    tags: String,
    views: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(380.dp)
            .padding(vertical = 6.dp, horizontal = 12.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        ConstraintLayout(modifier = Modifier.fillMaxSize()) {
            val (video, tagsText, viewsText) = createRefs()



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