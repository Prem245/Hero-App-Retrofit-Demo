package com.example.heroestrayappretrofitmvvm.network

import okhttp3.OkHttpClient
import okhttp3.internal.http.RetryAndFollowUpInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object Retrofit {
    private var retrofit: Retrofit? = null

    private val okHttpClient: OkHttpClient = OkHttpClient.Builder()
        .readTimeout((60 * 2).toLong(), TimeUnit.SECONDS)
        .connectTimeout((60 * 2).toLong(), TimeUnit.SECONDS)
        .writeTimeout((60 * 2).toLong(), TimeUnit.SECONDS)
        .addInterceptor(RetryAndFollowUpInterceptor(OkHttpClient()))
        .build()

    fun getRetrofitClient(baseUrl: String): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
        }
        return retrofit!!
    }
}