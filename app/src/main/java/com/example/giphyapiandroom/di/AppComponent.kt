package com.example.giphyapiandroom.di

import com.example.giphyapiandroom.repository.TrendingRepository
import dagger.Component

@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(trendingRepository: TrendingRepository)
}