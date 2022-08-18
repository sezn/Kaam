package com.szn.kaam

import com.google.gson.GsonBuilder
import com.szn.kaam.network.APIService
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class APITest {
    private lateinit var service: APIService

    @Before
    fun setUp() {
        val gson = GsonBuilder().setLenient().create()
        service = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(APIService::class.java)
    }

    @Test
    fun testCitations(){
        runBlocking {
            val citations = service.getAll()
            assert(citations.citation.isNotEmpty())
        }
    }

   /* @Test
    fun testPicture(){
        runBlocking {
            val pic = service.getPicture("Capito")
            assert(pic.isNotEmpty())
        }
    }*/

    @After
    fun tearDown() {
    }
}