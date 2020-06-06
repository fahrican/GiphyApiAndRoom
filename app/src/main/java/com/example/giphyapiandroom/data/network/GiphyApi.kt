package com.example.giphyapiandroom.data.network

import com.example.giphyapiandroom.model.TrendingResult
import io.reactivex.Flowable
import retrofit2.http.GET

interface GiphyApi {

    @GET("v1/gifs/trending")
    fun getTrending(): Flowable<TrendingResult>
}