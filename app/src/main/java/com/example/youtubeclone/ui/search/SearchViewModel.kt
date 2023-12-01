package com.example.youtubeclone.ui.search

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.example.beletvideoexample.model.trending.TrendingVideoModelResponse
import com.example.beletvideoexample.network.RetrofitInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.http.Query


class SearchViewModel : ViewModel() {
    var trendingVideoList: MutableLiveData<List<TrendingVideoModelResponse>> =
        MutableLiveData<List<TrendingVideoModelResponse>>()

    fun getTrendingVideoList() = CoroutineScope(Dispatchers.Default).launch {
        val response = RetrofitInstance.api.getTrendingVideos()
        Log.d("response", response.toString())
        Log.e("response", response.toString())
        if (response.isSuccessful) {
            Log.e("success", response.toString())
            trendingVideoList.postValue(response.body())
        } else {
            Log.e("error", "${response.code()} ${response.message()}")
        }
    }
}


