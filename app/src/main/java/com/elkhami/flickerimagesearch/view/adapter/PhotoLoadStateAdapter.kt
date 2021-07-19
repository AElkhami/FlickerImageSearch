package com.elkhami.flickerimagesearch.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.elkhami.flickerimagesearch.databinding.LoadStateItemBinding

/**
 * Created by A.Elkhami on 19,July,2021
 */
class PhotoLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<PhotoLoadStateAdapter.ViewHolder>() {

    class ViewHolder(val binding: LoadStateItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(holder: ViewHolder, loadState: LoadState) {

        if (loadState is LoadState.Loading) {
            holder.binding.progress.visibility = View.VISIBLE
            holder.binding.errorTextView.visibility = View.GONE
            holder.binding.retryButton.visibility = View.GONE
        } else {
            holder.binding.progress.visibility = View.GONE
            holder.binding.errorTextView.visibility = View.VISIBLE
            holder.binding.retryButton.visibility = View.VISIBLE
        }

        if (loadState is LoadState.Error) {
            holder.binding.errorTextView.text = loadState.error.localizedMessage
        }

        holder.binding.retryButton.setOnClickListener {
            retry.invoke()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ViewHolder =
        ViewHolder(
            LoadStateItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

}