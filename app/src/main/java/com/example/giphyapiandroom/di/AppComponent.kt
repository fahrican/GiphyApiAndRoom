package com.example.giphyapiandroom.di

import com.example.giphyapiandroom.repository.TrendingRepository
import com.example.giphyapiandroom.view.ui.MainActivity
import com.example.giphyapiandroom.viewmodel.TrendingViewModel
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(trendingRepository: TrendingRepository)

    fun inject(viewModel: TrendingViewModel)

    fun inject(mainActivity: MainActivity)
}