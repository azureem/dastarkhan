package com.example.dastarkhan.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dastarkhan.data.FoodData
import com.example.dastarkhan.data.TxtData
import com.example.dastarkhan.databinding.ItemFoodBinding
import com.example.dastarkhan.databinding.ScreenShowroomBinding

class TextileAdapter : ListAdapter<TxtData, TextileAdapter.VH>(DF){
    private var itemTouched: ((TxtData) -> Unit)? = null

    object DF: DiffUtil.ItemCallback<TxtData>() {
        override fun areItemsTheSame(oldItem: TxtData, newItem: TxtData): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: TxtData, newItem: TxtData): Boolean {
           return oldItem == newItem
        }
    }

    inner class VH (private val binding: ItemFoodBinding): RecyclerView.ViewHolder(binding.root){

        init {
            binding.root.setOnClickListener{
                itemTouched?.invoke(getItem(adapterPosition))
            }
        }
        fun bind(data: TxtData) {
            binding.foodName.text = data.name
            Glide.with(binding.root)
                .load(data.img)
                .into(binding.imageHolder)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemFoodBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
       holder.bind(getItem(position))
    }


    fun itemTouchedFun(block:(TxtData)->Unit){
        this.itemTouched = block
    }
}