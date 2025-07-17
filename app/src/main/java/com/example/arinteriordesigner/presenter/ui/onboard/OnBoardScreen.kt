package com.example.arinteriordesigner.presenter.ui.onboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.example.arinteriordesigner.R
import kotlinx.coroutines.launch

@Composable
fun OnBoardScreen(
    pages: List<OnBoardPage>,
    onFinish: () -> Unit
) {
    val state =
        rememberPagerState(pageCount = { 3 })
    val scope = rememberCoroutineScope();
    Column(modifier = Modifier.fillMaxSize()) {
        HorizontalPager(
            state = state,
            modifier = Modifier.weight(4f)
        ) { page ->
            OnBoardPageScreen(page = pages[page])
        }
        Spacer(modifier = Modifier.height(28.dp))
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            PageIndicator(3, state.currentPage)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                if (state.currentPage < pages.lastIndex) {
                    TextButton(
                        onClick = { onFinish() }
                    ) {
                        Text(text = "Skip")
                    }
                    TextButton(
                        onClick = {
                            scope.launch {
                                state.animateScrollToPage(state.currentPage + 1)
                            }
                        }
                    ) {
                        Text(text = "Next")
                    }
                } else {
                    TextButton(
                        onClick = { onFinish() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(align = Alignment.CenterHorizontally)
                    ) {
                        Text(text = "Get started!")
                    }
                }
            }
        }
    }
}

@Composable
fun PageIndicator(pageCount: Int, currentIndex: Int, modifier: Modifier = Modifier) {
    Box {
        Row(verticalAlignment = Alignment.CenterVertically) {
            repeat(pageCount) { iteration ->
                val color =
                    if (currentIndex == iteration) colorResource(R.color.grey_2) else colorResource(
                        R.color.grey_1
                    )
                val size = if (currentIndex == iteration) 10.dp else 8.dp
                Box(
                    modifier = modifier
                        .padding(3.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(size)
                )
            }
        }
    }
}