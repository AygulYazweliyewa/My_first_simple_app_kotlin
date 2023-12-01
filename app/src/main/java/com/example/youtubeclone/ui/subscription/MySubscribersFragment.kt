package com.example.youtubeclone.ui.subscription


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
import com.example.beletvideoexample.adapter.NotificationRecyclerAdapter
import com.example.beletvideoexample.databinding.FragmentMySubscribersBinding
import com.example.beletvideoexample.utils.SpacingItemDecoration

class MySubscribersFragment : Fragment() {
    private var _binding: FragmentMySubscribersBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private lateinit var notificationRecyclerAdapter: NotificationRecyclerAdapter
    private val mySubscribersViewModel: MySubscribersViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMySubscribersBinding.inflate(inflater, container, false)
        binding.mySubsBackIcon.setOnClickListener {
            navController.navigate(R.id.action_mySubscribersFragment_to_navigation_download)
        }
        binding.mySubsBackIcon.setOnClickListener {
            navController.navigate(R.id.action_mySubscribersFragment_to_navigation_home)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mySubscribersViewModel.getMySubsList()
        initRecyclerView()

        mySubscribersViewModel.mySubsList.observe(viewLifecycleOwner) {
            notificationRecyclerAdapter.submitList(it)
        }

        navController = Navigation.findNavController(view)
    }

    private fun initRecyclerView() {
        binding.mySubsRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            val toSpacing = SpacingItemDecoration(1)
            addItemDecoration(toSpacing)
            notificationRecyclerAdapter = NotificationRecyclerAdapter()
            adapter = notificationRecyclerAdapter
        }
    }
}