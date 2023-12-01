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

class ViewAllRecyclerAdapter :
    ListAdapter<TrendingVideoModelResponse, ViewAllRecyclerAdapter.ViewAllHolder>(
        DiffCallback
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewAllHolder {
        return ViewAllHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.view_list, parent, false)
        )
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ViewAllHolder, position: Int) {
        val list = getItem(position)
        holder.bind(list)
    }

    class ViewAllHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val viewListThumbnail: ImageView = itemView.findViewById(R.id.viewList_thumbnail)
        private val viewListText: TextView = itemView.findViewById(R.id.viewList_text)
        private val viewListText2: TextView = itemView.findViewById(R.id.viewList_text2)
        private val viewListDuration: TextView = itemView.findViewById(R.id.viewList_duration)

        fun bind(list: TrendingVideoModelResponse) {

            viewListText.text = list.title
            viewListText2.text = list.uploaderName
            viewListDuration.text = Utilities.convertYouTubeDuration(list.duration.toString())

            Glide.with(itemView.context)
                .load(list.thumbnail)
                .into(viewListThumbnail)
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