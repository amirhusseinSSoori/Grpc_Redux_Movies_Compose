package com.amirhusseinsoori.data.mapper


import com.amirhusseinsoori.domain.entity.DomainMoviesItem
import com.amirhusseinsoori.domain.entity.model.BannerModel
import io.grpc.movienode.VideoListXModel
import io.grpc.movienode.VideoHeaderXModel


fun VideoListXModel.moviesItemMapToDomain(): DomainMoviesItem {
    return DomainMoviesItem(
        Description = description ?: "",
        ID = id ?: 0,
        Name = name ?: "",
        Picture = picture ?: "",
        Views = views ?: 0
    )
}

fun VideoHeaderXModel.moviesItemBannerMapToDomain(): BannerModel {
    return BannerModel(
        Description = description ?: "",
        ID = id ?: 0,
        Name = name ?: "",
        Picture = picture ?: "",
        ID_VIDEO =  idvideo?: 0
    )
}

fun List<VideoHeaderXModel> .moviesBannerMapToDomain(): List<BannerModel> {
    return map { it.moviesItemBannerMapToDomain() }
}
fun List<VideoListXModel> .moviesMapToDomain(): List<DomainMoviesItem> {
    return map { it.moviesItemMapToDomain() }
}