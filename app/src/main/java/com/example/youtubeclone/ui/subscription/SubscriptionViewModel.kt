package com.example.youtubeclone.ui.subscription


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.beletvideoexample.model.channelId.ChannelVideoModelResponse
import com.example.beletvideoexample.model.trending.TrendingVideoModelResponse
import com.example.beletvideoexample.network.RetrofitInstance
import kotlinx.coroutines.launch


class SubscriptionViewModel : ViewModel() {
    val channelLiveData: MutableLiveData<ChannelVideoModelResponse> = MutableLiveData()
    var trendingVideoList: MutableLiveData<List<TrendingVideoModelResponse>> =
        MutableLiveData<List<TrendingVideoModelResponse>>()



    fun getChannel(id: String) = viewModelScope.launch {
        val response = RetrofitInstance.api.getChannelId(id)
        Log.e("response", response.toString())
        if (response.isSuccessful) {
            Log.e("success", response.toString())
            channelLiveData.postValue(response.body())
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
