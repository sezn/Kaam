package com.szn.kaam.repo

import android.util.Log
import com.szn.kaam.datastore.DataStoreManager
import com.szn.kaam.db.Quote
import com.szn.kaam.db.QuoteDAO
import com.szn.kaam.network.APIService
import com.szn.kaam.network.State
import com.szn.kaam.network.model.Citations
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import java.text.SimpleDateFormat
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.time.DurationUnit

@Singleton
class KaamRepository @Inject constructor(private val api: APIService,
    private val quoteDAO: QuoteDAO,
    private val dataStore: DataStoreManager){

    val TAG = KaamRepository::class.java.simpleName
    val state by lazy { MutableStateFlow<State>(State.START) }
    val citations by lazy { MutableStateFlow(emptyList<Quote>()) }

    init {
        CoroutineScope(Dispatchers.IO).launch {
            val lastUpdated = dataStore.lastUpdated()
            Log.w(TAG, "lastUpdated ${SimpleDateFormat("dd/M/yyyy HH:mm:ss").format(lastUpdated)}")
            checkDb()
        }
    }

    suspend fun getAll() {
        val cits = api.getAll()
        Log.w(TAG, "status=${cits.status}  cits = ${cits.citation.size}")
        if(cits.citation.isNullOrEmpty()){
            state.emit(State.FAILURE("Empty"))
        } else{
            val cis = cits.citation.map {
                    (cit, c) -> Quote(citation = cit,
                                        acteur= c.acteur,
                                        auteur = c.auteur,
                                        episode = c.episode,
                                        personnage = c.personnage,
                                        saison = c.saison)
            }
//            Insert List<Quote> in Db
            quoteDAO.insertAll(cis)
            citations.emit(cis.toMutableList())
            state.emit(State.SUCCESS)
            dataStore.setLastUpdated()
        }
    }

    private suspend fun checkDb() {
        val dbo = quoteDAO.getAll()
        Log.w(TAG, "From Database: ${dbo.size}")
        if(dbo.isNotEmpty() && dataStore.lastUpdated() < TimeUnit.HOURS.toMillis(1)) {
            citations.emit(dbo)
            state.emit(State.SUCCESS)
        } else {
            getAll()
        }
    }


}