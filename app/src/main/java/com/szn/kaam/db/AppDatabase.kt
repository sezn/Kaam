package com.szn.kaam.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * RoomDatabase implementation
 */
@Database(entities = [Quote::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun quoteDao(): QuoteDAO

    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "KAAM"
        ).build()

    }
}