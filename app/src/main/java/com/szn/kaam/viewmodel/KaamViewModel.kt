package com.szn.kaam.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.szn.kaam.model.Citation
import com.szn.kaam.network.APIService
import com.szn.kaam.network.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class KaamViewModel @Inject constructor(private val api: APIService): ViewModel() {

    val TAG = KaamViewModel::class.java.simpleName
    val state by lazy { MutableStateFlow<State>(State.START) }
    val citations by lazy { MutableStateFlow(mutableListOf<Citation>()) }

    init {
        Log.w(TAG, "init...")
        viewModelScope.launch {
            getAll()
        }
    }

    suspend fun getAll() {
        val cits = api.getAll()
        Log.w(TAG, "status=${cits.status}  cits = ${cits.citation.size}")
        if(cits.citation.isNullOrEmpty()){
            state.emit(State.FAILURE("Empty"))
        } else{
            state.emit(State.SUCCESS)
            citations.emit(cits.citation.toMutableList())
        }
    }

}