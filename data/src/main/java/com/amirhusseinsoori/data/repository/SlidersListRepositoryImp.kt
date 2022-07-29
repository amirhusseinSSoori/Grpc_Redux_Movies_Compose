package com.amirhusseinsoori.data.repository

import com.amirhusseinsoori.data.dataSource.slider.HeaderListSource
import com.amirhusseinsoori.data.mapper.moviesBannerMapToDomain
import com.amirhusseinsoori.domain.entity.model.BannerModel
import com.amirhusseinsoori.domain.repository.SliderListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SlidersListRepositoryImp @Inject constructor(private val remote: HeaderListSource) :
    SliderListRepository {
    override fun getBannerListMovies(type: String): Flow<List<BannerModel>> = flow {
        emit(remote.getSliderMovie(type).videoHeaderXList.moviesBannerMapToDomain())
    }
}
