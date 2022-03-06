package com.amirhusseinsoori.data.di

import com.amirhusseinsoori.data.dataSource.remote.MovieRemoteSource
import com.amirhusseinsoori.data.dataSource.remote.MovieRemoteSourceImp
import com.amirhusseinsoori.data.repository.MovieListRepositoryImp
import com.amirhusseinsoori.domain.repository.MovieListRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteSourceModule {
    @Binds
    abstract fun bindRemoteSource(movieRemoteSourceImp: MovieRemoteSourceImp): MovieRemoteSource
}
