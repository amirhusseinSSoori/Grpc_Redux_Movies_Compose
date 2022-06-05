package com.amirhusseinsoori.grpckotlin.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.amirhusseinsoori.grpckotlin.ui.theme.Purple500

@Composable
fun Loader(anim: Int) {

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(anim))
    val progress by animateLottieCompositionAsState(composition)
    Column(
        modifier = Modifier
            .width(200.dp)
            .height(200.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LottieAnimation(
            composition,
            progress,
        )
    }
}


@Composable
fun ShowLoading(showDialog: Boolean) {
    if (showDialog) {
        Dialog(
            onDismissRequest = { showDialog },
            DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
        ) {

            Column(
                modifier = Modifier
                    .size(150.dp)
                    .background(White, shape = RoundedCornerShape(10.dp)),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = "Loading...", textAlign = TextAlign.Center)
            }

        }
    }

}

@Composable
fun ShowErrorDialog(showDialog: Boolean, type: String, callEvent: () -> Unit) {
    if (showDialog) {
        Dialog(
            onDismissRequest = { showDialog },
            DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
                    .background(White, shape = RoundedCornerShape(10.dp))
            ) {
                Text(
                    text = "Error Message",
                    textAlign = TextAlign.Center,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(top = 5.dp).fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(5.dp))
                Divider(
                    modifier = Modifier
                        .height(2.dp)
                        .padding(start = 4.dp, end = 4.dp)
                        .background(Purple500)
                )
                Text(
                    text = type,
                    Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp).fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(5.dp))
                Divider(
                    modifier = Modifier
                        .height(2.dp)
                        .padding(start = 4.dp, end = 4.dp)
                        .background(Purple500)
                )
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    onClick = { callEvent() }) {
                    Text(text = "please try again")
                }
            }
        }
    }

}

