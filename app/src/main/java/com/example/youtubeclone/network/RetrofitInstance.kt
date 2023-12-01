package com.example.youtubeclone.network


import com.example.youtubeclone.BASE_URL
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create


object RetrofitInstance {

    val gson: Gson = GsonBuilder()
        .setLenient()
        .create()

    val api: PlayerApi by lazy {
        Retrofit.Builder()
            .baseUrl(com.example.youtubeclone.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(PlayerApi::class.java)
        }
    }

