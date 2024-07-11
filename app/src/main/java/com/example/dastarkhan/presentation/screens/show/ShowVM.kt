package com.example.dastarkhan.presentation.screens.show

import com.example.dastarkhan.data.ImageData
import kotlinx.coroutines.flow.Flow

interface ShowVM {
    val imgFlow : Flow<List<ImageData>>
    fun getImageData()
}