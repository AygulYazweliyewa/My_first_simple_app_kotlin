package com.example.youtubeclone.model.videoId

data class PreviewFrame(
    val durationPerFrame: Int = 0,
    val frameHeight: Int = 0,
    val frameWidth: Int = 0,
    val framesPerPageX: Int = 0,
    val framesPerPageY: Int = 0,
    val totalCount: Int = 0,
    val urls: List<String> = listOf()
)