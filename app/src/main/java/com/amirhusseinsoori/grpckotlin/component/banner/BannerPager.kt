package com.amirhusseinsoori.grpckotlin.component.banner

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.amirhusseinsoori.domain.entity.model.BannerModel
import com.amirhusseinsoori.grpckotlin.component.banner.ui.BannerCard
import com.amirhusseinsoori.grpckotlin.component.banner.ui.config.BannerConfig
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch
import java.util.*
import kotlin.math.absoluteValue


@OptIn(ExperimentalPagerApi::class)
@Composable
fun BannerPager(
    modifier: Modifier = Modifier,
    items: List<BannerModel> = arrayListOf(),
    config: BannerConfig = BannerConfig(),
    indicatorIsVertical: Boolean = false,
    indicatorGravity: Alignment = Alignment.BottomCenter,
    onBannerClick: (BannerModel) -> Unit
) {
    if (items.isEmpty()) {
       return
    }

    val pagerState = rememberPagerState()

    if (config.repeat) {
        StartBanner(pagerState, config.intervalTime)
    }

    Box(modifier = modifier.height(config.bannerHeight)) {
        HorizontalPager(
            count = items.size,
            state = pagerState,
            itemSpacing = config.itemSpacing,
            contentPadding = config.contentPadding
        ) { page ->
            val item = items[page]
            BannerCard(
                bean = item,
                modifier = Modifier
                    .graphicsLayer {
                        // Calculate the absolute offset for the current page from the
                        // scroll position. We use the absolute value which allows us to mirror
                        // any effects for both directions
                        val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue

                        // We animate the scaleX + scaleY, between 85% and 100%
                        lerp(
                            start = 0.85f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        ).also { scale ->
                            scaleX = scale
                            scaleY = scale
                        }

                        // We animate the alpha, between 50% and 100%
                        alpha = lerp(
                            start = 0.5f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )
                    }
                    .fillMaxSize()
                    .padding(config.bannerImagePadding),
                shape = config.shape,
                contentScale = config.contentScale
            ) {
                onBannerClick(item)
            }
        }
        if (indicatorIsVertical) {
            VerticalPagerIndicator(
                pagerState = pagerState,
                modifier = Modifier
                    .align(indicatorGravity)
            )
        } else {
            HorizontalPagerIndicator(
                pagerState = pagerState,
                modifier = Modifier
                    .align(indicatorGravity)
            )
        }

    }
}


@ExperimentalPagerApi
@Composable
fun StartBanner(pagerState: PagerState, intervalTime: Long) {
    val coroutineScope = rememberCoroutineScope()
    val timer = Timer()
    val timerTask = object : TimerTask() {
        override fun run() {
            coroutineScope.launch {
                pagerState.animateScrollToPage((pagerState.currentPage + 1) % pagerState.pageCount)
            }
        }
    }
    timer.schedule(timerTask, intervalTime, intervalTime)
}