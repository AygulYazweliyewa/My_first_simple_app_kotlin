package com.example.youtubeclone.model.videoId

data class AudioStream(
    val audioTrackId: Any = Any(),
    val audioTrackLocale: Any = Any(),
    val audioTrackName: Any = Any(),
    val audioTrackType: Any = Any(),
    val bitrate: Int = 0,
    val codec: String = "",
    val contentLength: Int = 0,
    val format: String = "",
    val fps: Int = 0,
    val height: Int = 0,
    val indexEnd: Int = 0,
    val indexStart: Int = 0,
    val initEnd: Int = 0,
    val initStart: Int = 0,
    val itag: Int = 0,
    val mimeType: String = "",
    val quality: String = "",
    val url: String = "",
    val videoOnly: Boolean = false,
    val width: Int = 0
)