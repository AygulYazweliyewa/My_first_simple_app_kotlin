package com.example.youtubeclone.adapter


import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.beletvideoexample.R
import com.example.beletvideoexample.model.trending.TrendingVideoModelResponse
import com.example.beletvideoexample.utils.Utilities


class PlaylistRecyclerAdapter :
    ListAdapter<TrendingVideoModelResponse, PlaylistRecyclerAdapter.PlaylistViewHolder>(
        DiffCallback
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistViewHolder {
        return PlaylistViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_playlist, parent, false)
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: PlaylistViewHolder, position: Int) {
        val playlist = getItem(position)
        holder.bind(playlist)
    }

    class PlaylistViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val playlistThumbnail: ImageView = itemView.findViewById(R.id.playlist_thumbnail)
        private val playlistText: TextView = itemView.findViewById(R.id.playlist_text)
        private val playlistUserName: TextView = itemView.findViewById(R.id.playlist_user_name)
        private val playlistDuration: TextView = itemView.findViewById(R.id.playlist_duration)

        fun bind(playlist: TrendingVideoModelResponse) {

            playlistText.text = playlist.title
            playlistUserName.text = playlist.uploaderName
            playlistDuration.text =  Utilities.convertYouTubeDuration(playlist.duration.toString())

            Glide.with(itemView.context)
                .load(playlist.thumbnail)
                .into(playlistThumbnail)

        }

    }

    companion object DiffCallback : DiffUtil.ItemCallback<TrendingVideoModelResponse>() {
        override fun areItemsTheSame(
            oldItem: TrendingVideoModelResponse,
            newItem: TrendingVideoModelResponse
        ): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(
            oldItem: TrendingVideoModelResponse,
            newItem: TrendingVideoModelResponse
        ): Boolean {
            return oldItem == newItem
        }

    }
}