package com.amirhusseinsoori.grpckotlin.navigation

import androidx.navigation.NamedNavArgument

sealed class NavRoute(var route: String, val arguments: List<NamedNavArgument>) {
    object IntroRoute : NavRoute("Intro_screen", emptyList())
    object MainRoute : NavRoute("Main_screen", emptyList())
    object SearchRoute : NavRoute("Search_screen", emptyList())
}