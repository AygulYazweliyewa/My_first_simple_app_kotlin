package com.example.youtubeclone.adapter


import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.youtubeclone.MainActivity
import com.example.beletvideoexample.R
import com.example.beletvideoexample.model.trending.TrendingVideoModelResponse
import com.example.beletvideoexample.utils.Utilities


class TrendingRecyclerAdapter :
    ListAdapter<TrendingVideoModelResponse, TrendingRecyclerAdapter.TrendingViewHolder>(
        DiffCallback
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendingViewHolder {
        return TrendingViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_home_video, parent, false)
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: TrendingViewHolder, position: Int) {
        val listTrending = getItem(position)
        holder.bind(listTrending)
    }


    class TrendingViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val videoThumbnail: ImageView = itemView.findViewById(R.id.thumbnail)
        private val videoAuthor: ImageView = itemView.findViewById(R.id.uploaderAvatar)
        private val videoTitle: TextView = itemView.findViewById(R.id.title)
        private val videoInfo: TextView = itemView.findViewById(R.id.uploaderName)
        private val videoDuration: TextView = itemView.findViewById(R.id.duration)
        private val videoView: TextView = itemView.findViewById(R.id.views)
        private val videoDate: TextView = itemView.findViewById(R.id.uploaderDate)

        fun bind(trendingVideos: TrendingVideoModelResponse) {

            videoTitle.text = trendingVideos.title
            videoInfo.text = trendingVideos.uploaderName
            videoDuration.text =
                Utilities.convertYouTubeDuration(trendingVideos.duration.toString())
            videoView.text = Utilities.viewCounts(trendingVideos.views)
            videoDate.text = trendingVideos.uploadedDate

            videoAuthor.setOnClickListener {
                (it.context as com.example.youtubeclone.MainActivity).findNavController(R.id.nav_host_fragment_activity_main)
                    .navigate(
                        R.id.navigation_subscription,
                        bundleOf("channel" to trendingVideos.uploaderUrl.replace("/channel/", ""))
                    )
            }

            videoThumbnail.setOnClickListener {
                (it.context as com.example.youtubeclone.MainActivity).findNavController(R.id.nav_host_fragment_activity_main)
                    .navigate(
                        R.id.navigation_library,
                        bundleOf("video" to trendingVideos.url.replace("/watch?v=", ""))
                    )
            }

            Glide.with(itemView.context)
                .load(trendingVideos.thumbnail)
                .into(videoThumbnail)

            Glide.with(itemView.context)
                .load(trendingVideos.uploaderAvatar)
                .into(videoAuthor)
//
//            Glide.with(itemView.context)
//                .load(trendingVideos.uploaderAvatar)
//                .into(subsFUserImage)
        }
    }


    companion object DiffCallback : DiffUtil.ItemCallback<TrendingVideoModelResponse>() {
        override fun areItemsTheSame(
            oldItem: TrendingVideoModelResponse,
            newItem: TrendingVideoModelResponse
        ): Boolean {
            return oldItem.thumbnail == newItem.thumbnail
        }

        override fun areContentsTheSame(
            oldItem: TrendingVideoModelResponse,
            newItem: TrendingVideoModelResponse
        ): Boolean {
            return oldItem == newItem
        }

    }
}