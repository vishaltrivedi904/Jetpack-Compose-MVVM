package com.example.catfact.data.repository

import com.example.catfact.data.api.ApiInterface
import com.example.catfact.data.model.catbread.BreadResponse
import com.example.catfact.data.model.catfact.FactResponse
import retrofit2.Response
import javax.inject.Inject

class Repository @Inject constructor(private val apiInterface: ApiInterface) {

    suspend fun getBreeds(page: Int): Response<BreadResponse> {
        return apiInterface.getBreads(page)
    }

    suspend fun getRandomFact(): Response<FactResponse> {
        return apiInterface.getRandomFact()
    }

}