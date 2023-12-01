package com.example.youtubeclone.ui.download


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.beletvideoexample.model.trending.TrendingVideoModelResponse
import com.example.beletvideoexample.network.RetrofitInstance
import kotlinx.coroutines.launch

class DownloadViewModel : ViewModel() {

    var playlistViewAll: MutableLiveData<List<TrendingVideoModelResponse>> = MutableLiveData<List<TrendingVideoModelResponse>>()

    fun getPlaylistViewAll() = viewModelScope.launch {
        val response = RetrofitInstance.api.getTrendingVideos()
        Log.e("response", response.toString())
        if (response.isSuccessful) {
            Log.e("success", response.toString())
            playlistViewAll.postValue(response.body())
        } else {
            Log.e("error", "${response.code()} ${response.message()}")
        }
    }
}
