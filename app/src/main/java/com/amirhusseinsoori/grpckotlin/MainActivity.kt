package com.amirhusseinsoori.grpckotlin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.amirhusseinsoori.grpckotlin.navigation.InitialNavGraph
import com.amirhusseinsoori.grpckotlin.ui.theme.ThemeGrpcMovies
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ThemeGrpcMovies {
                InitialNavGraph()
            }
        }
    }
}