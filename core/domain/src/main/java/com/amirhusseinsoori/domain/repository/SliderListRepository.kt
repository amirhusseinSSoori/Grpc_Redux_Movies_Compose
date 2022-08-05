package com.amirhusseinsoori.domain.repository

import com.amirhusseinsoori.domain.entity.model.BannerModel
import kotlinx.coroutines.flow.Flow

interface SliderListRepository  {
    fun getBannerListMovies(type:String):Flow<List<BannerModel>>
}