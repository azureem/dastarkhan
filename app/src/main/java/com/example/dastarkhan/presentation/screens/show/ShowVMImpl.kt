package com.example.dastarkhan.presentation.screens.show

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dastarkhan.data.ImageData
import com.example.dastarkhan.domain.AppRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ShowVMImpl @Inject constructor(private val repo: AppRepo) : ShowVM, ViewModel(){

    override val imgFlow = MutableSharedFlow<List<ImageData>>(replay = 1, onBufferOverflow = BufferOverflow.DROP_LATEST)
    override fun getImageData() {
        repo.getImages().onEach {
            it.onSuccess {
                imgFlow.emit(it)
            }
            it.onFailure {
                it.message.toString()
            }
        }.launchIn(viewModelScope)
    }
}