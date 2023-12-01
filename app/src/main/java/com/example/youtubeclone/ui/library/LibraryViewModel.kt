package com.example.youtubeclone.ui.library

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.beletvideoexample.model.trending.TrendingVideoModelResponse
import com.example.beletvideoexample.model.videoId.VideoIdResponse
import com.example.beletvideoexample.network.RetrofitInstance
import kotlinx.coroutines.launch

class LibraryViewModel : ViewModel() {
    var videoIdMutableLiveData: MutableLiveData<VideoIdResponse> =
        MutableLiveData<VideoIdResponse>()
    var trendingVideoList: MutableLiveData<List<TrendingVideoModelResponse>> =
        MutableLiveData<List<TrendingVideoModelResponse>>()


    fun getVideo(id: String) = viewModelScope.launch {
        val response = RetrofitInstance.api.getVideoId(id)
        Log.e("response", response.toString())
        if (response.isSuccessful) {
            Log.e("success", response.toString())
            videoIdMutableLiveData.postValue(response.body())
        } else {
            Log.e("error", "${response.code()} ${response.message()}")
        }
    }

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