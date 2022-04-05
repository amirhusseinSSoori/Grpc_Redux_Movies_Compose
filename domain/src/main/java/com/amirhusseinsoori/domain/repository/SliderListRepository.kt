package com.amirhusseinsoori.domain.repository

import com.amirhusseinsoori.domain.entity.model.BannerModel
import kotlinx.coroutines.flow.Flow

interface SliderListRepository  {
    fun getBannerListMovies():Flow<List<BannerModel>>
}