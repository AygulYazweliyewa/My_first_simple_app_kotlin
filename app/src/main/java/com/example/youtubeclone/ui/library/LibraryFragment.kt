package com.example.youtubeclone.ui.library


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.beletvideoexample.R
import com.example.youtubeclone.WatchActivity
import com.example.beletvideoexample.adapter.SubsListRecyclerAdapter
import com.example.beletvideoexample.adapter.TrendingRecyclerAdapter
import com.example.beletvideoexample.databinding.FragmentLibraryBinding
import com.example.beletvideoexample.utils.SpacingItemDecoration
import com.example.beletvideoexample.utils.Utilities
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.PlayerView

class LibraryFragment : Fragment() {
    private var _binding: FragmentLibraryBinding? = null
    private val binding get() = _binding!!
    private lateinit var trendingRecyclerAdapter: TrendingRecyclerAdapter
    private lateinit var subsListRecyclerAdapter: SubsListRecyclerAdapter
    private val libraryViewModel: LibraryViewModel by viewModels()
    private lateinit var navController: NavController
    private lateinit var playerView: PlayerView
    private var id: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        id = arguments?.getString("video")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLibraryBinding.inflate(inflater, container, false)
        binding.libraryRecyclerView.setOnClickListener {
            navController.navigate(R.id.action_navigation_library_to_navigation_home)
        }
        binding.exoFullScreen.setOnClickListener {
            navController.navigate(R.id.action_navigation_library_to_playerFragment)

        }
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val button = binding.exoFullScreen
        button.setOnClickListener {
            val intent = Intent(this@LibraryFragment.context, com.example.youtubeclone.WatchActivity::class.java)
            intent.putExtra("video", id)
            startActivity(intent)
        }
        navController = Navigation.findNavController(view)

        playerView = binding.exoPlayer

        libraryViewModel.getVideo(id.toString())
        Log.i("video", "id $id")
        libraryViewModel.videoIdMutableLiveData.observe(viewLifecycleOwner) { send ->
            if (send == null) return@observe
            binding.playerVideoTitle.text = send.title
            binding.playerVideoViews.text = Utilities.viewCounts(send.views)
            binding.playerVideoUploaderDate.text = send.uploadDate
            binding.playerVideoProfileTitle.text = send.uploader
            binding.playerVideoSubs.text = Utilities.subscribersCount(send.uploaderSubscriberCount)
            binding.chipPlayerLike.text = Utilities.likeCounts(send.likes)
            binding.chipPlayerDiselike.text = Utilities.dislikeCounts(send.dislikes)

            val player = ExoPlayer.Builder(requireContext()).build()
            playerView.setPlayer(player)

            val mediaItem = MediaItem.fromUri(send.hls)
            player.setMediaItem(mediaItem)
            player.prepare()
            player.play()
            player.pause()
            player.stop()


            Glide.with(view.context)
                .load(send.uploaderAvatar)
                .into(binding.playerVideoProfile)

            libraryViewModel.getTrendingVideoList()
            initLibraryRecyclerView()
            libraryViewModel.trendingVideoList.observe(viewLifecycleOwner) {
                subsListRecyclerAdapter.submitList(it) {

                }
            }
            initLibraryRecyclerView()
            libraryViewModel.trendingVideoList.observe(viewLifecycleOwner) {
                trendingRecyclerAdapter.submitList(it) {

                }
            }
        }
        navController = Navigation.findNavController(view)
    }


    private fun initLibraryRecyclerView() {
        binding.libraryRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            val toSpacing = SpacingItemDecoration(1)
            addItemDecoration(toSpacing)
            trendingRecyclerAdapter = TrendingRecyclerAdapter(
            )
            adapter = trendingRecyclerAdapter
        }
        binding.SubsListRecyclerView.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            val toSpacing = SpacingItemDecoration(1)
            addItemDecoration(toSpacing)
            subsListRecyclerAdapter = SubsListRecyclerAdapter()
            adapter = subsListRecyclerAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

