package com.amirhusseinsoori.grpckotlin.ui.search

import SearchBar
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier



@OptIn(ExperimentalComposeUiApi::class, ExperimentalAnimationApi::class)
@Composable
fun Search() {
    Column(
        modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var expanded by remember { mutableStateOf(false) }
        var value by remember { mutableStateOf("") }
        Surface() {
            Column(modifier = Modifier.fillMaxSize()) {
                SearchBar(
                    query = value,
                    onQueryChange = {
                        value = it
                    },
                    onSearchFocusChange = { expanded = false },
                    onClearQuery = {
                        value = ""
                    },
                    enableClose = value != "",
                )
            }

        }

    }

}