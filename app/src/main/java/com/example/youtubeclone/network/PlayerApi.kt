package com.example.youtubeclone.network


import com.example.beletvideoexample.model.channelId.ChannelVideoModelResponse
import com.example.beletvideoexample.model.trending.TrendingVideoModelResponseItem
import com.example.beletvideoexample.model.videoId.VideoIdResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface PlayerApi {
    @Headers(
        "Accept: text/html,application",
        "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36"
    )
    @GET("trending")
    suspend fun getTrendingVideos(
        @Query("region") region: String = "US"
    ): Response<TrendingVideoModelResponseItem>

    @Headers(
        "Accept: text/html,application",
        "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36"
    )
    @GET("channel/{channelId}")
    suspend fun getChannelId(
        @Path("channelId") channelId: String?
    ): Response<ChannelVideoModelResponse>

    @Headers(
        "Accept: text/html,application",
        "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/119.0.0.0 Safari/537.36"
    )
    @GET("streams/{videoId}")
    suspend fun getVideoId(
        @Path("videoId") videoId: String?
    ): Response<VideoIdResponse>
}