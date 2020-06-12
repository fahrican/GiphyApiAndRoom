package com.example.giphyapiandroom.di

import com.example.giphyapiandroom.data.network.GiphyApi
import com.example.giphyapiandroom.data.network.GiphyApiService
import com.example.giphyapiandroom.model.Data
import com.example.giphyapiandroom.repository.TrendingRepository
import com.example.giphyapiandroom.view.adapter.TrendingAdapter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule { //todo: add abstract

    @Singleton
    @Provides
    fun provideApi(): GiphyApi = GiphyApiService.getClient()

    @Provides
    fun provideTrendingRepository(): TrendingRepository = TrendingRepository() //todo: simplify this

    @Provides
    fun provideListData() = ArrayList<Data>()

    @Provides
    fun provideTrendingAdapter(data: ArrayList<Data>): TrendingAdapter = TrendingAdapter(data)
}