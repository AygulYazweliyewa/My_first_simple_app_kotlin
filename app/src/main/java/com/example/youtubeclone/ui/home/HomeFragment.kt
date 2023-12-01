package com.example.youtubeclone.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.beletvideoexample.R
import com.example.beletvideoexample.adapter.TrendingRecyclerAdapter
import com.example.beletvideoexample.databinding.FragmentHomeBinding
import com.example.beletvideoexample.utils.SpacingItemDecoration

class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding? = null
    private lateinit var trendingRecyclerAdapter: TrendingRecyclerAdapter
    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var navController: NavController
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.latestRecyclerView.setOnClickListener {
            navController.navigate(R.id.action_navigation_home_to_navigation_subscription)
        }
        binding.searchToolbarIcon.setOnClickListener {
            navController.navigate(R.id.action_navigation_home_to_searchFragment)
        }
        binding.notificationToolbarIcon.setOnClickListener {
            navController.navigate(R.id.action_navigation_home_to_mySubscribersFragment)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        homeViewModel.getTrendingVideoList()
        initRecyclerView()
        binding.progressBarHome.isVisible = false

        homeViewModel.trendingVideoList.observe(viewLifecycleOwner) {
            trendingRecyclerAdapter.submitList(it) {

            }
        }

        navController = Navigation.findNavController(view)
    }

    private fun initRecyclerView() {
        binding.latestRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            val toSpacing = SpacingItemDecoration(1)
            addItemDecoration(toSpacing)
            trendingRecyclerAdapter = TrendingRecyclerAdapter()
            adapter = trendingRecyclerAdapter
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}