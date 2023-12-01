package com.example.youtubeclone.ui.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.beletvideoexample.model.trending.TrendingVideoModelResponse
import com.example.beletvideoexample.network.RetrofitInstance
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    var trendingVideoList: MutableLiveData<List<TrendingVideoModelResponse>> =
        MutableLiveData<List<TrendingVideoModelResponse>>()

    fun getTrendingVideoList() = viewModelScope.launch {
        val response = RetrofitInstance.api.getTrendingVideos()
        Log.e("response", response.toString())
        if (response.isSuccessful) {
            Log.e("success", response.toString())
            trendingVideoList.postValue(response.body())
        } else {
            Log.e("error", "${response.code()} ${response.message()}")
        }

    }
}