package com.amirhusseinsoori.data.di

import com.amirhusseinsoori.domain.repository.MovieListRepository
import com.amirhusseinsoori.data.repository.MovieListRepositoryImp
import com.amirhusseinsoori.data.repository.SlidersListRepositoryImp
import com.amirhusseinsoori.domain.repository.SliderListRepository

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindMovieListRepository(movieListRepositoryImp: MovieListRepositoryImp): MovieListRepository

    @Binds
    abstract fun bindSliderListRepository(slidersListRepositoryImp: SlidersListRepositoryImp): SliderListRepository
}