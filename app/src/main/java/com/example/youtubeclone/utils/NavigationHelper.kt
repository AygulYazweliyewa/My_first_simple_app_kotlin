package com.example.youtubeclone.utils

import android.content.Context
import android.content.Intent
import com.example.youtubeclone.MainActivity


object NavigationHelper {

    fun navigateVideo(context: Context, channelId: String?, uploaderName: String){
        if (channelId != null) {
            val userIntent = Intent(context, com.example.youtubeclone.MainActivity::class.java)
            userIntent.putExtra("channelId", channelId)
            context.startActivity(userIntent)
        }
    }
}