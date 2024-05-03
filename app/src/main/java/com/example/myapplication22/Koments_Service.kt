package com.example.myapplication

import retrofit2.http.GET

interface Service {
    @GET("/random_joke")
    suspend fun getdate():
            Koments
}