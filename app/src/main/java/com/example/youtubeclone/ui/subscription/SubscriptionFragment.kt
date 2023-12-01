package com.example.youtubeclone.ui.subscription

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.beletvideoexample.R
import com.example.beletvideoexample.adapter.TrendingRecyclerAdapter
import com.example.beletvideoexample.databinding.FragmentSubscriptionBinding
import com.example.beletvideoexample.utils.SpacingItemDecoration
import com.example.beletvideoexample.utils.Utilities


class SubscriptionFragment : Fragment() {
    private var _binding: FragmentSubscriptionBinding? = null
    private val subscriptionViewModel: SubscriptionViewModel by viewModels()
    private lateinit var trendingRecyclerAdapter: TrendingRecyclerAdapter
    private lateinit var navController: NavController
    private val binding get() = _binding!!

    private var id: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        id = arguments?.getString("channel")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSubscriptionBinding.inflate(inflater, container, false)
        binding.userButton.setOnClickListener {
            navController.navigate(R.id.action_navigation_subscription_to_navigation_home)
        }
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscriptionViewModel.getChannel(id.toString())
        Log.i("channel", "id $id")
        subscriptionViewModel.channelLiveData.observe(viewLifecycleOwner) { channelView ->
            if (channelView == null) return@observe
            binding.userTitle.text = channelView.name
            binding.userId.text = "@" + channelView.relatedStreams[0].uploaderName
            binding.userSubscribers.text = Utilities.subscribersCount(channelView.subscriberCount)
            binding.userDescription.text = channelView.description
            binding.progressBarChannel.isVisible = false

            Glide.with(view.context)
                .load(channelView.avatarUrl)
                .into(binding.userAvatar)

            Glide.with(view.context)
                .load(channelView.bannerUrl)
                .into(binding.userBackground)

            subscriptionViewModel.getTrendingVideoList()
            initSubsRecyclerView()
            subscriptionViewModel.trendingVideoList.observe(viewLifecycleOwner) {
                trendingRecyclerAdapter.submitList(it) {

                }
            }
            navController = Navigation.findNavController(view)
        }

    }

    private fun initSubsRecyclerView() {
        binding.subsRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            val toSpacing = SpacingItemDecoration(1)
            addItemDecoration(toSpacing)
            trendingRecyclerAdapter = TrendingRecyclerAdapter(
            )
            adapter = trendingRecyclerAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}