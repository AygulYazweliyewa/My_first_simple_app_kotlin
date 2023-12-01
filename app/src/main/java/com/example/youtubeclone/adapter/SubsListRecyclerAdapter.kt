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

class SubsListRecyclerAdapter :
    ListAdapter<TrendingVideoModelResponse, SubsListRecyclerAdapter.SubsViewHolder>(
        DiffCallback
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubsViewHolder {
        return SubsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.subs_user_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: SubsViewHolder, position: Int) {
        val userSubsList = getItem(position)
        holder.bind(userSubsList)
    }

    class SubsViewHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val subsFUserAvatar: ImageView = itemView.findViewById(R.id.subsFUserAvatar)
        private val subsFUserName: TextView = itemView.findViewById(R.id.subsFUserName)

        fun bind(userSubsList: TrendingVideoModelResponse) {

            subsFUserName.text = userSubsList.uploaderName

            Glide.with(itemView.context)
                .load(userSubsList.uploaderAvatar)
                .into(subsFUserAvatar)
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