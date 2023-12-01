package com.example.youtubeclone.model.channelId

data class ChannelVideoModelResponse(
    val avatarUrl: String = "",
    val bannerUrl: String = "",
    val description: String = "",
    val id: String = "",
    val name: String = "",
    val nextpage: String = "",
    val relatedStreams: List<RelatedStream> = listOf(),
    val subscriberCount: Int = 0,
    val tabs: List<Tab> = listOf(),
    val verified: Boolean = false
)