package com.mecofarid.trending.ui.trending.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mecofarid.trending.android.databinding.ItemTrendingBinding

class TrendingList(
    private val provideLifecycleOwner: LifecycleOwner
) : ListAdapter<TrendingView, TrendingList.ViewHolder>(RepoDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemTrendingBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ).apply {
            lifecycleOwner = provideLifecycleOwner
        }
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(getItem(position))


    class ViewHolder(private val binding: ItemTrendingBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(trendingView: TrendingView) {
            binding.trendingView = trendingView
        }
    }

    class RepoDiffCallback: DiffUtil.ItemCallback<TrendingView>() {
        override fun areItemsTheSame(oldItem: TrendingView, newItem: TrendingView): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: TrendingView, newItem: TrendingView): Boolean =
            oldItem == newItem
    }
}
