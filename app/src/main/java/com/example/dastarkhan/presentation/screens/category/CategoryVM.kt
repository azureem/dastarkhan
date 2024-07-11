package com.example.dastarkhan.presentation.screens.category

import com.example.dastarkhan.data.FoodData
import com.example.dastarkhan.data.ImageData
import kotlinx.coroutines.flow.Flow

interface CategoryVM {
    val dataFlow: Flow<List<FoodData>>
    fun getFoodData()
    val progressBar : Flow<Boolean>
}