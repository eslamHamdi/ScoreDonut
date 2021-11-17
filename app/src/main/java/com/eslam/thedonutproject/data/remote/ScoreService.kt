package com.eslam.thedonutproject.data.remote

import retrofit2.http.GET

interface ScoreService {

    @GET("endpoint.json")
    suspend fun getScore():ScoreResponse
}