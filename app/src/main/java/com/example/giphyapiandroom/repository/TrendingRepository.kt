package com.example.giphyapiandroom.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.giphyapiandroom.GiphyApplication
import com.example.giphyapiandroom.data.database.toDataEntityList
import com.example.giphyapiandroom.data.database.toDataList
import com.example.giphyapiandroom.data.network.GiphyApi
import com.example.giphyapiandroom.di.DaggerAppComponent
import com.example.giphyapiandroom.internal.KEY
import com.example.giphyapiandroom.internal.LIMIT
import com.example.giphyapiandroom.internal.RATING
import com.example.giphyapiandroom.model.Data
import com.example.giphyapiandroom.model.TrendingResult
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.DisposableSubscriber
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

    private fun insertData(): Disposable {
        return giphyApiService.getTrending(KEY, LIMIT, RATING)
            .subscribeOn(Schedulers.io())
            .subscribeWith(subscribeToDatabase())
    }

    private fun subscribeToDatabase(): DisposableSubscriber<TrendingResult> {
        return object : DisposableSubscriber<TrendingResult>() {

            override fun onNext(trendingResult: TrendingResult?) {
                if (trendingResult != null) {
                    val entityList = trendingResult.data.toList().toDataEntityList()
                    GiphyApplication.database.apply {
                        dataDao().insertData(entityList)
                    }
                }
            }

            override fun onError(t: Throwable?) {
                _isInProgress.postValue(true)
                Log.e("insertData()", "TrendingResult error: ${t?.message}")
                _isError.postValue(true)
                _isInProgress.postValue(false)
            }

            override fun onComplete() {
                Log.v("insertData()", "insert success")
                getTrendingQuery()
            }
        }
    }

    private fun getTrendingQuery(): Disposable {
        return GiphyApplication.database.dataDao()
            .queryData()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { dataEntityList ->
                    _isInProgress.postValue(true)
                    if (dataEntityList != null && dataEntityList.isNotEmpty()) {
                        _isError.postValue(false)
                        _data.postValue(dataEntityList.toDataList())
                    } else {
                        insertData()
                    }
                    _isInProgress.postValue(false)

                },
                {
                    _isInProgress.postValue(true)
                    Log.e("getTrendingQuery()", "Database error: ${it.message}")
                    _isError.postValue(true)
                    _isInProgress.postValue(false)
                }
            )
    }

    fun fetchDataFromDatabase() {
        getTrendingQuery()
    }

}