package com.amirhusseinsoori.grpckotlin.ui.details

import android.graphics.Picture
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.amirhusseinsoori.domain.entity.DomainMoviesItem


@Composable
fun ScreenDetails(movieItems:DomainMoviesItem) {
    Column(Modifier.fillMaxSize()) {

        Text(text = movieItems.Picture.replace(" ","/"))

        
    }
}