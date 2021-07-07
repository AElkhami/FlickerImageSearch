package com.elkhami.flickerimagesearch.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.elkhami.flickerimagesearch.data.remote.responses.Photo
import com.elkhami.flickerimagesearch.databinding.PhotoItemBinding

/**
 * Created by A.Elkhami on 07,July,2021
 */
class PhotoAdapter() :
    RecyclerView.Adapter<PhotoAdapter.ViewHolder>() {


    private val diffUtilCallback = object : DiffUtil.ItemCallback<Photo>() {
        override fun areItemsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Photo, newItem: Photo): Boolean {
            return oldItem.id == newItem.id
        }
    }

    private val differ = AsyncListDiffer(this, diffUtilCallback)

    var photosList: List<Photo>
        get() = differ.currentList
        set(value) = differ.submitList(value)


    class ViewHolder(val binding: PhotoItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            PhotoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val photo = photosList[position]
    }

    override fun getItemCount(): Int = photosList.size
}