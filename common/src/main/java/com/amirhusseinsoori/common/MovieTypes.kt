package com.amirhusseinsoori.common

sealed class MovieTypes(val type: String) {
    object ComedyType : MovieTypes("Action")
    object SerialsType : MovieTypes("Drama")
    object PopularType : MovieTypes("Comedy")
}
