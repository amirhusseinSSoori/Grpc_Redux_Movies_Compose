package com.amirhusseinsoori.common

sealed class MovieTypes(val type: String) {
    object ComedyType : MovieTypes("Comedy")
    object SerialsType : MovieTypes("Serial")
    object PopularType : MovieTypes("Popular")
}
