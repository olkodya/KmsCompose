package com.example.keymanagement.network.repository

import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import okhttp3.logging.HttpLoggingInterceptor


class RetrofitHelper {
    companion object {
        private val API_BASE_URL = ""
    }

    private val retrofit: Retrofit

    init {
        val gson = GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create()

        val builder = Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .writeTimeout(0, TimeUnit.MILLISECONDS)
            .writeTimeout(2, TimeUnit.MINUTES)
            .connectTimeout(1, TimeUnit.MINUTES).build()
        retrofit = builder.client(okHttpClient).build()
    }

    fun getAuthService() : AuthApi {
        return retrofit.create(AuthApi::class.java)
    }
}