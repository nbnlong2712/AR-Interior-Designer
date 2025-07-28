package com.example.arinteriordesigner.presenter.ui.onboard

import androidx.annotation.ColorRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.arinteriordesigner.R
import kotlinx.coroutines.launch

@Composable
@PreviewScreenSizes
fun OnBoardScreenPreview(
    @PreviewParameter(OnBoardPagePreview::class) onBoardList: OnBoardPage
) {
    val list = mutableListOf<OnBoardPage>()
    list.add(onBoardList)
    OnBoardScreen(
        pages = list,
        onFinish = {})
}

@Composable
fun OnBoardScreen(
    pages: List<OnBoardPage>,
    onFinish: () -> Unit
) {
    val state =
        rememberPagerState(pageCount = { 3 })
    val scope = rememberCoroutineScope();
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(pages.get(state.currentPage).backgroundColor))
    ) {
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
                        .padding(start = 10.dp, top = 12.dp, bottom = 24.dp, end = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    if (state.currentPage < pages.lastIndex) {
                        OnBoardButton(
                            onClick = {
                                scope.launch {
                                    state.animateScrollToPage(state.currentPage + 1)
                                }
                            },
                            backgroundColor = pages.get(state.currentPage).buttonColor,
                            text = "Next"
                        )
                    } else {
                        OnBoardButton(
                            onClick = { onFinish() },
                            backgroundColor = pages.get(state.currentPage).buttonColor,
                            text = "Get Started!"
                        )
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

@Composable
fun OnBoardButton(
    onClick: () -> Unit, @ColorRes backgroundColor: Int, text: String
) {
    FloatingActionButton(
        onClick = { onClick() },
        modifier = Modifier
            .widthIn(max = 400.dp)
            .fillMaxWidth()
            .padding(all = 14.dp),
        backgroundColor = colorResource(backgroundColor)
    ) {
        Text(text = text, fontSize = 16.sp)
    }
}