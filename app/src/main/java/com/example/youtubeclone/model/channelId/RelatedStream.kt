package com.example.youtubeclone.model.channelId

data class RelatedStream(
    val duration: Int = 0,
    val isShort: Boolean = false,
    val shortDescription: String = "",
    val thumbnail: String = "",
    val title: String = "",
    val type: String = "",
    val uploaded: Long = 0,
    val uploadedDate: String = "",
    val uploaderAvatar: Any = Any(),
    val uploaderName: String = "",
    val uploaderUrl: String = "",
    val uploaderVerified: Boolean = false,
    val url: String = "",
    val views: Int = 0
)