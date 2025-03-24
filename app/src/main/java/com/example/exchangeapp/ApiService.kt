package com.example.exchangeapp

import retrofit2.http.GET
import retrofit2.Call

interface ApiService {
    @GET("kursExchange")
    fun getExchangeRates(): Call<List<ExchangeRate>>
}