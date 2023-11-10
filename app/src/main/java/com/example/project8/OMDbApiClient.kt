package com.example.project8

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class OMDbApiClient {

    companion object{
        private var omdbapi: OMDbApi? = null

        fun getOMDbApi():OMDbApi?{
            if(omdbapi == null){
                omdbapi = Retrofit.Builder()
                    .baseUrl("http://www.omdbapi.com/?&apikey=810b8b6d")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(OMDbApi::class.java)
            }
            return omdbapi
        }
    }
}