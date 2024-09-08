package com.example.catfact.data.api

import com.example.catfact.data.model.catbread.BreadResponse
import com.example.catfact.data.model.catfact.FactResponse
import retrofit2.Response
import retrofit2.http.*

interface ApiInterface {

    @GET("breeds")
    suspend fun getBreads(@Query("page") page: Int): Response<BreadResponse>

    @GET("fact")
    suspend fun getRandomFact(): Response<FactResponse>


}