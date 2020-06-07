package com.example.giphyapiandroom.di

import com.example.giphyapiandroom.data.network.GiphyApi
import com.example.giphyapiandroom.data.network.GiphyApiService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule { //todo: add abstract

    @Singleton
    @Provides
    fun provideApi(): GiphyApi = GiphyApiService.getClient()
}