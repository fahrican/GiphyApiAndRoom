package com.example.giphyapiandroom.di

import com.example.giphyapiandroom.repository.TrendingRepository
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(trendingRepository: TrendingRepository)
}