package com.amirhusseinsoori.grpckotlin.data.mapper

import com.amirhusseinsoori.grpckotlin.data.network.response.Movies
import com.amirhusseinsoori.grpckotlin.data.network.response.MoviesItem
import com.amirhusseinsoori.grpckotlin.domain.DomainMoviesItem


fun MoviesItem.moviesItemMapToDomain(): DomainMoviesItem {
    return DomainMoviesItem(
        Description = Description ?: "",
        ID = ID ?: 0,
        Name = Name ?: "",
        Picture = Picture ?: "",
        Views = Views ?: 0
    )
}
fun Movies.moviesMapToDomain(): List<DomainMoviesItem> {
    return map { it.moviesItemMapToDomain() }
}