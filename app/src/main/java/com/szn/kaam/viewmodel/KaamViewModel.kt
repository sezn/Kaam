package com.szn.kaam.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.szn.kaam.db.Quote
import com.szn.kaam.repo.KaamRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class KaamViewModel @Inject constructor(//private val api: APIService,
                                        private val repository: KaamRepository): ViewModel() {

    val TAG = KaamViewModel::class.java.simpleName
    val state = repository.state
    val citations = repository.citations
    val persos = repository.persos

    init {
        Log.w(TAG, "init...")
        viewModelScope.launch {
            repository.getAll()
        }
    }

    suspend fun getAll() {
        repository.getAll()
    }

    fun filter(value: String): MutableList<Quote> {
        return citations.value.filter { it.personnage == value }.toMutableList()
    }

}