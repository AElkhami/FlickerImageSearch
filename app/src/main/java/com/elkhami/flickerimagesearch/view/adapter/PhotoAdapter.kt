package com.elkhami.flickerimagesearch.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.elkhami.flickerimagesearch.data.local.SavedPhoto
import com.elkhami.flickerimagesearch.databinding.PhotoItemBinding
import javax.inject.Inject

/**
 * Created by A.Elkhami on 07,July,2021
 */

class PhotoAdapter @Inject constructor(private val glide: RequestManager) :
    PagingDataAdapter<SavedPhoto, PhotoAdapter.ViewHolder>(DiffUtilCallback) {

    class ViewHolder(val binding: PhotoItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            PhotoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        getItem(position)?.let { savedPhoto ->

            holder.binding.imageTitle.text = savedPhoto.photoTitle

            glide.load(savedPhoto.photoUrl).into(holder.binding.imageView)

            holder.itemView.apply {

                setOnClickListener {
                    onItemClickListener?.let {
                        it(savedPhoto)
                    }
                }
            }
        }
    }

    object DiffUtilCallback : DiffUtil.ItemCallback<SavedPhoto>() {
        override fun areItemsTheSame(oldItem: SavedPhoto, newItem: SavedPhoto): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: SavedPhoto, newItem: SavedPhoto): Boolean {
            return oldItem == newItem
        }
    }

    private var onItemClickListener: ((SavedPhoto) -> Unit)? = null

    fun setOnItemClickListener(listener: ((SavedPhoto) -> Unit)) {
        onItemClickListener = listener
    }
}