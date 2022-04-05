package com.amirhusseinsoori.domain.usecase

import com.amirhusseinsoori.domain.entity.DomainMoviesItem
import com.amirhusseinsoori.domain.entity.model.BannerModel
import com.amirhusseinsoori.domain.exception.GrpcResult
import com.amirhusseinsoori.domain.repository.MovieListRepository
import com.amirhusseinsoori.domain.repository.SliderListRepository
import com.amirhusseinsoori.domain.usecase.base.UseCaseImmediate
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ShowListSliderUseCase @Inject constructor(private val repository: SliderListRepository) :
    UseCaseImmediate<Flow<List<BannerModel>>>(){
    override suspend fun buildUseCaseImmediate(): Flow<List<BannerModel>> {
        return repository.getBannerListMovies()
    }
}