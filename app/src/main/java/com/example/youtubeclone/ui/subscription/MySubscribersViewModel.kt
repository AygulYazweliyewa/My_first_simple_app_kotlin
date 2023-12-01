package com.example.youtubeclone.ui.subscription

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.beletvideoexample.model.trending.TrendingVideoModelResponse
import com.example.beletvideoexample.network.RetrofitInstance
import kotlinx.coroutines.launch

class MySubscribersViewModel : ViewModel() {
    var mySubsList: MutableLiveData<List<TrendingVideoModelResponse>> =
        MutableLiveData<List<TrendingVideoModelResponse>>()
    fun getMySubsList() = viewModelScope.launch {
        val response = RetrofitInstance.api.getTrendingVideos()
        Log.e("response", response.toString())
        if (response.isSuccessful) {
            Log.e("success", response.toString())
           mySubsList.postValue(response.body())
        } else {
            Log.e("error", "${response.code()} ${response.message()}")
        }
    }
}