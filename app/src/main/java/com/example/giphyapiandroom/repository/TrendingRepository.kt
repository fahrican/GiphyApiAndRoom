package com.example.giphyapiandroom.repository

import com.example.giphyapiandroom.data.network.GiphyApi
import javax.inject.Inject

class TrendingRepository {

    @Inject
    lateinit var giphyApiService: GiphyApi
}