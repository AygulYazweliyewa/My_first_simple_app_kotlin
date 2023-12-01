package com.example.youtubeclone


import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.beletvideoexample.databinding.ActivityWatchBinding
import com.example.beletvideoexample.ui.library.LibraryViewModel
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer


class WatchActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWatchBinding
    private val libraryViewModel: LibraryViewModel by viewModels()
    private var id: String? = ""

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWatchBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val intent = getIntent()
        id = intent.getStringExtra("video")
        val player = SimpleExoPlayer.Builder(this).build()
        binding.playerView.player = player

        libraryViewModel.getVideo(id.toString())
        Log.i("video", "id $id")
        libraryViewModel.videoIdMutableLiveData.observe(this, Observer { run ->
            if (run == null) return@Observer

            val mediaItem = MediaItem.fromUri(run.hls)
            player.setMediaItem(mediaItem)
            player.prepare()
            player.play()
//            player.pause()
            player.stop()

        })

    }
}