package com.example.youtubeclone.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.beletvideoexample.R
import com.example.beletvideoexample.model.trending.TrendingVideoModelResponse

class NotificationRecyclerAdapter :
    ListAdapter<TrendingVideoModelResponse, NotificationRecyclerAdapter.NotificationHolder>(
        DiffCallback
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationHolder {
        return NotificationHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.subs_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NotificationHolder, position: Int) {
        val subsList = getItem(position)
        holder.bind(subsList)
    }

    class NotificationHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val subsListThumbnail: ImageView = itemView.findViewById(R.id.subs_avatar)
        private val subsListName: TextView = itemView.findViewById(R.id.subs_name)

        fun bind(subsList: TrendingVideoModelResponse) {
            subsListName.text = subsList.uploaderName

            Glide.with(itemView.context)
                .load(subsList.uploaderAvatar)
                .into(subsListThumbnail)
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