package com.amirhusseinsoori.grpckotlin.data.di

import com.amirhusseinsoori.grpckotlin.data.repository.MovieListRepositoryImp
import com.amirhusseinsoori.grpckotlin.domain.repository.MovieListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindMovieListRepository(movieListRepositoryImp: MovieListRepositoryImp): MovieListRepository
}