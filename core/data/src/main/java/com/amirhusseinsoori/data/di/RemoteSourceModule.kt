package com.amirhusseinsoori.data.di

import com.amirhusseinsoori.data.dataSource.category.MovieRemoteSource
import com.amirhusseinsoori.data.dataSource.category.MovieRemoteSourceImp
import com.amirhusseinsoori.data.dataSource.slider.HeaderListSource
import com.amirhusseinsoori.data.dataSource.slider.HeaderListSourceImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteSourceModule {
    @Binds
    abstract fun bindRemoteSource(movieRemoteSourceImp: MovieRemoteSourceImp): MovieRemoteSource


    @Binds
    abstract fun bindRemoteSliderSource(headerListSourceImp: HeaderListSourceImp): HeaderListSource
}
