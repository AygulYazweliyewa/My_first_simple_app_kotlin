package com.example.youtubeclone.ui.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.beletvideoexample.adapter.TrendingRecyclerAdapter
import com.example.beletvideoexample.databinding.FragmentSearchBinding

class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private lateinit var trendingRecyclerAdapter: TrendingRecyclerAdapter
    private lateinit var searchViewModel: SearchViewModel
    private lateinit var navController: NavController
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initSearchRecyclerView()

        searchViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        binding.searchText.requestFocus()

        navController = findNavController()


        val appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.youTubeSearch.setupWithNavController(navController, appBarConfiguration)

//        binding.searchProgress.isVisible = true
//        binding.searchProgress.isVisible = false

        searchViewModel.trendingVideoList.observe(viewLifecycleOwner) {
            trendingRecyclerAdapter.submitList(it) {

            }
        }

        binding.searchText.setOnEditorActionListener(
            TextView.OnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    fetchSearch(binding.searchText.text.toString())
                    return@OnEditorActionListener true
                }
                false
            }
        )
    }

    private fun fetchSearch(toString: String) {
//        binding.recyclerQueryList.visibility = View.GONE
//        binding.youTubeSearch.visibility = View.VISIBLE
        lifecycleScope.launchWhenCreated {
            searchViewModel.getTrendingVideoList()
        }

        binding.youTubeSearch.visibility = View.VISIBLE
    }

    private fun initSearchRecyclerView() {
        binding.latestRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            trendingRecyclerAdapter = TrendingRecyclerAdapter()
            adapter = trendingRecyclerAdapter
        }
    }

override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
}
}