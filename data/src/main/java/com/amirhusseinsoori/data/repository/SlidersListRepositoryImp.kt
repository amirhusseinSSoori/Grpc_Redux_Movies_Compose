package com.amirhusseinsoori.data.repository

import com.amirhusseinsoori.domain.entity.model.BannerModel
import com.amirhusseinsoori.domain.repository.SliderListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SlidersListRepositoryImp @Inject constructor() : SliderListRepository {
    override fun getBannerListMovies(): Flow<List<BannerModel>> = flow {
        emit(sliders)
    }.flowOn(Dispatchers.IO)
}


var sliders = arrayListOf(
    BannerModel(
        "http://amirhusseinsoori.ir/image/banner/john_wick_4.jpeg",
        "john Wick 4"
    ),
    BannerModel(
        "http://amirhusseinsoori.ir/image/banner/free_guy.jpeg",
        "Free Guy"
    ),
    BannerModel(
        "http://amirhusseinsoori.ir/image/banner/spiderman.jpeg",
        "spider man no away home"
    ),
    BannerModel(
        "http://amirhusseinsoori.ir/image/banner/007.jpeg",
        "No time to die movie"
    ),
)


