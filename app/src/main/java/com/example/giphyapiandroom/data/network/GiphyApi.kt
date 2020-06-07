package com.example.giphyapiandroom.data.network

import com.example.giphyapiandroom.model.TrendingResult
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query


interface GiphyApi {

    @GET("v1/gifs/trending")
    fun getTrending(
        @Query("api_key") api_key: String, //todo: use camelCase
        @Query("limit") limit: String,
        @Query("rating") rating: String
    ): Flowable<TrendingResult>

}