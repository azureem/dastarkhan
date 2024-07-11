package com.example.dastarkhan.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dastarkhan.data.ImageData
import com.example.dastarkhan.databinding.ItemImgBinding
import com.example.dastarkhan.databinding.ScreenInfoBinding

class ImageAdapter : ListAdapter<ImageData, ImageAdapter.VH>(DF) {

    object DF : DiffUtil.ItemCallback<ImageData>() {
        override fun areItemsTheSame(oldItem: ImageData, newItem: ImageData): Boolean {
            return oldItem ==newItem
        }

        override fun areContentsTheSame(oldItem: ImageData, newItem: ImageData): Boolean {
            return oldItem==newItem
        }
    }

    inner class VH(private val binding: ItemImgBinding):RecyclerView.ViewHolder(binding.root) {

        fun bind(data: ImageData){
            Glide.with(binding.root)
                .load(data.img)
                .into(binding.imgDetail)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemImgBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(getItem(position))
    }
}