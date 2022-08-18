package com.szn.kaam.network;

import com.szn.kaam.model.Citations
import retrofit2.http.GET

interface APIService {


    @GET("all")
    suspend fun getAll(): Citations

}
