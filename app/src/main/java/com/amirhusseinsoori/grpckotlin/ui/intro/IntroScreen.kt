package com.amirhusseinsoori.grpckotlin.ui.intro


import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.amirhusseinsoori.grpckotlin.navigation.NavRoute
import kotlinx.coroutines.delay

@Composable
fun Intro(navController: NavController) {
//    val scale = remember { Animatable(0f) }
//    LaunchedEffect(key1 = true) {
//        scale.animateTo(
//            targetValue = 0.3f,
//            animationSpec = tween(
//                durationMillis = 500,
//                easing = {
//                    OvershootInterpolator(2f).getInterpolation(it)
//                }
//            )
//        )
//        delay(3000L)
//        navController.navigate(NavRoute.MainRoute.route) {
//            popUpTo(NavRoute.IntroRoute.route) { inclusive = true }
//            launchSingleTop = true
//        }
//    }
    Column(
        modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Button(onClick = { navController.navigate(NavRoute.MainRoute.route) }) {
            Text(text = "Intro page")
        }

    }

}