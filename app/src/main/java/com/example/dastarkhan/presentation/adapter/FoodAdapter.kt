package com.example.dastarkhan.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dastarkhan.data.FoodData
import com.example.dastarkhan.databinding.ItemCuisineBinding
import com.example.dastarkhan.databinding.ItemFoodBinding

class FoodAdapter : ListAdapter<FoodData, FoodAdapter.VH>(DF) {

    private var itemTouched: ((FoodData) -> Unit)? = null

    object DF : DiffUtil.ItemCallback<FoodData>() {
        override fun areItemsTheSame(oldItem: FoodData, newItem: FoodData): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: FoodData, newItem: FoodData): Boolean {
            return oldItem == newItem
        }
    }

    inner class VH(private val binding: ItemCuisineBinding) : RecyclerView.ViewHolder(binding.root) {

          init {
              binding.root.setOnClickListener{
                  itemTouched?.invoke(getItem(adapterPosition))
              }
          }
        fun bind(data: FoodData) {
          binding.foodName.text = data.name
//            Glide.with(binding.root)
//                .load(data.img)
//                .into(binding.imageHolder)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(ItemCuisineBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(getItem(position))
    }


    fun itemTouchedFun(block:(FoodData)->Unit){
        this.itemTouched = block
    }
}