package com.example.dastarkhan.presentation.screens.textile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dastarkhan.data.TxtData
import com.example.dastarkhan.domain.AppRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class TxtVMImpl @Inject constructor(private val repo: AppRepo) : TxtVM, ViewModel() {
    override val flowTxt =
        MutableSharedFlow<List<TxtData>>(replay = 1, onBufferOverflow = BufferOverflow.DROP_LATEST)
    override val progressBar = MutableStateFlow(true)


    override fun getTxt() {
        repo.getTxt().onEach {
            it.onSuccess {
                progressBar.emit(false)
                Log.d("UUU", "viewwwwwww:${progressBar.toString()} ")
                flowTxt.emit(it)
            }
            it.onFailure {
                it.message.toString()
                //progressBar.emit(false)
            }

        }.launchIn(viewModelScope)
    }
}