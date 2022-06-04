package com.amirhusseinsoori.common

sealed class MovieTypes(val type: String) {
    object ComedyType : MovieTypes("ListVideos3")
    object SerialsType : MovieTypes("ListVideos2")
    object PopularType : MovieTypes("ListVideos1")
}
