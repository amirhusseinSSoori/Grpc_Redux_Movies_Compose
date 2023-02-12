package com.amirhusseinsoori.grpckotlin.ui.intro


import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.amirhusseinsoori.grpckotlin.component.banner.utils.utilFont
import com.amirhusseinsoori.grpckotlin.navigation.NavRoute
import com.amirhusseinsoori.grpckotlin.ui.theme.ColorPrimary
import kotlinx.coroutines.delay


@Composable
fun Intro(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize().background(ColorPrimary),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        var visible by remember { mutableStateOf(false) }
        var createdValue by remember { mutableStateOf(false) }
        var showG by remember { mutableStateOf(false) }
        var showR by remember { mutableStateOf(false) }
        var showP by remember { mutableStateOf(false) }
        var showC by remember { mutableStateOf(false) }
        LaunchedEffect(key1 = true) {
            delay(100L)
            showR = true
            delay(300L)
            showG = true
            delay(500L)
            showP = true
            delay(700L)
            showC = true
            delay(700L)
            visible = true
            delay(900L)
            createdValue = true
            delay(2000L)
            navController.navigate(NavRoute.MainRoute.route) {
                popUpTo(NavRoute.IntroRoute.route) { inclusive = true }
                launchSingleTop = true
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(100.dp)
        ) {


            WordAnimation(visible = showR, word = "G")
            WordAnimation(visible = showG, word = "R")
            WordAnimation(visible = showP, word = "P")
            WordAnimation(visible = showC, word = "C")
        }
        TitleAnimation(visible = visible)
        CreatedByAnimation(visible = createdValue)


    }


}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun CreatedByAnimation(visible: Boolean) {
    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically(
            // Start the slide from 40 (pixels) above where the content is supposed to go, to
            // produce a parallax effect
            initialOffsetY = { -40 }
        ) + expandVertically(
            expandFrom = Alignment.Top
        ) + scaleIn(
            // Animate scale from 0f to 1f using the top center as the pivot point.
            transformOrigin = TransformOrigin(0.5f, 0f)
        ) + fadeIn(initialAlpha = 0.3f),
        exit = slideOutVertically() + shrinkVertically() + fadeOut() + scaleOut(targetScale = 1.2f)
    ) {
        // Content that needs to appear/disappear goes here:
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
                .background(color = ColorPrimary, shape = RoundedCornerShape(20.dp))
        ) {
            Text(
                text = "created By :  Amirhussein Soori", fontFamily = utilFont,
                fontWeight = FontWeight.Normal,color=Color.White, fontSize = 20.sp
            )
        }
    }
}

@Composable
fun TitleAnimation(visible: Boolean) {
    AnimatedVisibility(
        visible = visible,
        // Set the start width to 20 (pixels), 0 by default
        enter = expandHorizontally { 20 },
        exit = shrinkHorizontally(
            // Overwrites the default animation with tween for this shrink animation.
            animationSpec = tween(),
            // Shrink towards the end (i.e. right edge for LTR, left edge for RTL). The default
            // direction for the shrink is towards [Alignment.Start]
            shrinkTowards = Alignment.End,
        ) { fullWidth ->
            // Set the end width for the shrink animation to a quarter of the full width.
            fullWidth / 4
        }
    ) {
        // Content that needs to appear/disappear goes here:
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth()
                .background(color = ColorPrimary, shape = RoundedCornerShape(20.dp))
        ) {
            Text(
                text = "Movies", fontFamily = utilFont,color=Color.White,
                fontWeight = FontWeight.Normal, fontSize = 40.sp
            )
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun WordAnimation(visible: Boolean, word: String) {
    AnimatedVisibility(
        visible = visible,
        // Scale up from the TopLeft by setting TransformOrigin to (0f, 0f), while expanding the
        // layout size from Top start and fading. This will create a coherent look as if the
        // scale is impacting the size.
        enter = scaleIn(transformOrigin = TransformOrigin(0f, 0f)) +
                fadeIn() + expandIn(expandFrom = Alignment.TopStart),
        // Scale down from the TopLeft by setting TransformOrigin to (0f, 0f), while shrinking
        // the layout towards Top start and fading. This will create a coherent look as if the
        // scale is impacting the layout size.
        exit = scaleOut(transformOrigin = TransformOrigin(0f, 0f)) +
                fadeOut() + shrinkOut(shrinkTowards = Alignment.TopStart)
    ) {

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(100.dp)
                .background(color = ColorPrimary, shape = RoundedCornerShape(20.dp))
        ) {
            Text(
                text = word, fontFamily = utilFont,color=Color.White,
                fontWeight = FontWeight.Light, fontSize = 80.sp
            )
        }
    }
}