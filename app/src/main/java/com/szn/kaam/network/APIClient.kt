package com.szn.kaam.network

import android.content.Context
import com.szn.kaam.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * MovieClient for manage API com, will be injected by Hilt
 */
@Module
@InstallIn(SingletonComponent::class)
class APIClient {

    private val httpInterceptor by lazy {
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS)
    }

    @Provides
    @Singleton
    fun apiService(@ApplicationContext app: Context): APIService =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(OkHttpClient.Builder()
                .addInterceptor(httpInterceptor)
                .build())
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(APIService::class.java)

}