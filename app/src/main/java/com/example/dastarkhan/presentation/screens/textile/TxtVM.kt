package com.example.dastarkhan.presentation.screens.textile

import com.example.dastarkhan.data.TxtData
import kotlinx.coroutines.flow.Flow

interface TxtVM {

    fun getTxt()

    val progressBar: Flow<Boolean>

     val flowTxt : Flow<List<TxtData>>
}