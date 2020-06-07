package com.example.giphyapiandroom.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.giphyapiandroom.data.network.GiphyApi
import com.example.giphyapiandroom.di.DaggerAppComponent
import com.example.giphyapiandroom.model.Data
import javax.inject.Inject

class TrendingRepository {

    @Inject
    lateinit var giphyApiService: GiphyApi

    private val _data by lazy { MutableLiveData<List<Data>>() }
    val data: LiveData<List<Data>>
        get() = _data

    private val _isInProgress by lazy { MutableLiveData<Boolean>() }
    val isInProgress: LiveData<Boolean>
        get() = _isInProgress

    private val _isError by lazy { MutableLiveData<Boolean>() }
    val isError: LiveData<Boolean>
        get() = _isError


    init {
        DaggerAppComponent.create().inject(this)
    }
}