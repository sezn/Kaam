package com.szn.kaam.repo

import android.util.Log
import com.szn.kaam.db.Quote
import com.szn.kaam.db.QuoteDAO
import com.szn.kaam.network.model.Citation
import com.szn.kaam.network.APIService
import com.szn.kaam.network.State
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class KaamRepository @Inject constructor(val api: APIService,
                                         val quoteDAO: QuoteDAO){

    val TAG = KaamRepository::class.java.simpleName
    val state by lazy { MutableStateFlow<State>(State.START) }
    val citations by lazy { MutableStateFlow(mutableListOf<Quote>()) }
    val persos = mutableListOf<String>()

    init {
        Log.w(TAG, "init")
    }

    suspend fun getAll() {
        val cits = api.getAll()
        Log.w(TAG, "status=${cits.status}  cits = ${cits.citation.size}")
        if(cits.citation.isNullOrEmpty()){
            state.emit(State.FAILURE("Empty"))
        } else{
            // Build Perso List
            cits.citation.groupBy { it.infos.personnage }.forEach { (t, u) ->
                persos.add(t!!)
            }

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
        }
    }
}