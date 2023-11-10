package com.example.project8

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OMDbApi {
    @GET("?apikey=810b8b6d&")
    fun getMovie(@Query("t") title: String): Call<Movie>
}