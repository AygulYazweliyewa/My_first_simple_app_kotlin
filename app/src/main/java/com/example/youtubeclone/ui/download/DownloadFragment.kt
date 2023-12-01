package com.example.youtubeclone.ui.download

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.beletvideoexample.R
import com.example.beletvideoexample.adapter.PlaylistRecyclerAdapter
import com.example.beletvideoexample.databinding.FragmentDownloadBinding
import com.example.beletvideoexample.utils.SpacingItemDecoration

class DownloadFragment : Fragment() {

    private var _binding: FragmentDownloadBinding? = null
    private lateinit var playlistRecyclerAdapter: PlaylistRecyclerAdapter
    private val binding get() = _binding!!
    private val downloadViewModel: DownloadViewModel by viewModels()
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDownloadBinding.inflate(inflater, container, false)

        binding.searchUserToolbarIcon.setOnClickListener {
            navController.navigate(R.id.action_navigation_download_to_searchFragment)
        }

        binding.settingsUserToolbarIcon.setOnClickListener {
            navController.navigate(R.id.action_navigation_download_to_settingsFragment)
        }

        binding.notificationUserToolbarIcon.setOnClickListener {
            navController.navigate(R.id.action_navigation_download_to_mySubscribersFragment)
        }

        binding.viewAllChipPlaylist.setOnClickListener {
            navController.navigate(R.id.action_navigation_download_to_viewAllFragment)
        }

        return binding.root
    }

    @SuppressLint("NotifyDatasetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        downloadViewModel.getPlaylistViewAll()
        initPlaylistRecyclerView()

        downloadViewModel.playlistViewAll.observe(viewLifecycleOwner) {
            playlistRecyclerAdapter.submitList(it) {

            }
        }
        navController = Navigation.findNavController(view)
    }

    private fun initPlaylistRecyclerView() {
        binding.profilePlaylistRecyclerView.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            val toSpacing = SpacingItemDecoration(1)
            addItemDecoration(toSpacing)
            playlistRecyclerAdapter = PlaylistRecyclerAdapter()
            adapter = playlistRecyclerAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}