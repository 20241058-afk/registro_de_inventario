package com.example.registrodeinventario.Modelo

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.getValue

object RetrofitClient {

    private const val BASE_URL = "https://inventariocomputo.grupocctic.com/"

    val historialApi: ifaceApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ifaceApiService::class.java)
    }
}