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

const val urlImages="https://amirhusseinsoori.ir/data/movie/images/"


var sliders = arrayListOf(
    BannerModel(
        urlImages.plus("b_free_guy.jpg"),
        "john Wick 4"
    ),
    BannerModel(
        urlImages.plus("b_coda.jpg"),
        "Free Guy"
    ),
    BannerModel(
        urlImages.plus("b_spider_man.jpg"),
        "spider man no away home"
    ),
    BannerModel(
        urlImages.plus("b_peakyblinders.jpg"),
        "No time to die movie"
    ),
)


