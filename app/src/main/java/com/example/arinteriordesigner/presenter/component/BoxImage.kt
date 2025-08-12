package com.example.arinteriordesigner.presenter.component

import android.graphics.BitmapFactory
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun BoxImage(@DrawableRes imageSrc: Int, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val bitmap = remember(imageSrc) {
        BitmapFactory.decodeResource(context.resources, imageSrc)
    }

    val aspectRatio = remember(bitmap) {
        bitmap?.let { it.width.toFloat() / it.height.toFloat() } ?: 1f
    }

    Image(
        painter = painterResource(id = imageSrc),
        contentDescription = null,
        contentScale = ContentScale.Fit,
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(aspectRatio)
    )
}


fun getScaleType(maxWidth: Dp, maxHeight: Dp = 1.dp): ContentScale {
    val aspectRatio = maxWidth / maxHeight

    return if (aspectRatio > 1f) {
        ContentScale.FillHeight
    } else {
        ContentScale.FillWidth
    }
}