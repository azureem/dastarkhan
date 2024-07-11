package com.example.dastarkhan.domain

import com.example.dastarkhan.data.FoodData
import com.example.dastarkhan.data.ImageData
import com.example.dastarkhan.data.TxtData
import kotlinx.coroutines.flow.Flow


interface AppRepo {

    fun getFoodData(): Flow<Result<List<FoodData>>>
    fun getImages(): Flow<Result<List<ImageData>>>
    fun getTxt(): Flow<Result<List<TxtData>>>
}