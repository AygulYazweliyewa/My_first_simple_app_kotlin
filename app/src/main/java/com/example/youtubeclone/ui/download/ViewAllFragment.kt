package com.example.youtubeclone.ui.download

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
import com.example.beletvideoexample.adapter.ViewAllRecyclerAdapter
import com.example.beletvideoexample.databinding.FragmentViewAllBinding
import com.example.beletvideoexample.utils.SpacingItemDecoration

class ViewAllFragment : Fragment() {
    private var _binding: FragmentViewAllBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewAllRecyclerAdapter: ViewAllRecyclerAdapter
    private val viewAllViewModel: ViewAllViewModel by viewModels()
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentViewAllBinding.inflate(inflater, container, false)

        binding.viewAllBackIcon.setOnClickListener {
            navController.navigate(R.id.action_viewAllFragment_to_navigation_download)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewAllViewModel.getViewList()
        initViewListRecyclerView()

        viewAllViewModel.viewList.observe(viewLifecycleOwner) {
            viewAllRecyclerAdapter.submitList(it)
        }
        navController = Navigation.findNavController(view)
    }

    private fun initViewListRecyclerView() {
        binding.viewAllPlaylist.apply {
            layoutManager = LinearLayoutManager(requireContext())
            val toSpacing = SpacingItemDecoration(1)
            addItemDecoration(toSpacing)
            viewAllRecyclerAdapter = ViewAllRecyclerAdapter()
            adapter = viewAllRecyclerAdapter
        }
    }
}