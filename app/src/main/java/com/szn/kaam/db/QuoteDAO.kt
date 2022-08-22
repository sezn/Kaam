package com.szn.kaam.db

import androidx.room.*

@Dao
interface QuoteDAO {

    @Query("SELECT * FROM quote")
    fun getAll(): List<Quote>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(quotes: List<Quote>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(quote: Quote)

    @Delete
    fun delete(quote: Quote)
}