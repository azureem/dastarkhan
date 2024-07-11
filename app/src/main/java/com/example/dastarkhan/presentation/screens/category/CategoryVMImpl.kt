package com.example.dastarkhan.presentation.screens.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dastarkhan.data.FoodData
import com.example.dastarkhan.data.ImageData
import com.example.dastarkhan.domain.AppRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CategoryVMImpl @Inject constructor(val repo: AppRepo) : CategoryVM, ViewModel() {

    override val dataFlow = MutableSharedFlow<List<FoodData>>(replay = 1, onBufferOverflow = BufferOverflow.DROP_LATEST)
    override val progressBar = MutableStateFlow(true)


    override fun getFoodData() {
        repo.getFoodData().onEach {
            it.onSuccess {
                progressBar.emit(false)
                dataFlow.emit(it)
            }
            it.onFailure {
                it.message.toString()
            }
        }.launchIn(viewModelScope)
    }




}