package com.example.arinteriordesigner.presenter.ui.onboard

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun OnBoardPageScreen(page: OnBoardPage) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(0.75f)
                .wrapContentWidth(Alignment.CenterHorizontally)
                .wrapContentHeight(Alignment.CenterVertically),
            text = page.title,
            style = MaterialTheme.typography.h4.merge(TextStyle(fontWeight = FontWeight.SemiBold)),
            textAlign = TextAlign.Center
        )
        Image(
            modifier = Modifier
                .weight(3f)
                .fillMaxWidth(1f).padding(all = 24.dp),
            painter = painterResource(page.image),
            contentDescription = null,
            contentScale = ContentScale.Fit
        )
        Text(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(0.75f)
                .wrapContentWidth(Alignment.CenterHorizontally)
                .wrapContentHeight(Alignment.CenterVertically),
            text = page.description,
            style = MaterialTheme.typography.h6.merge(other = TextStyle(fontWeight = FontWeight.Medium)),
            textAlign = TextAlign.Center
        )
    }
}