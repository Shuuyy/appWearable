package com.example.myapplication


import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {

    @GET
  suspend fun getCurrentWheater(@Url url:String):Response<weatherResponse>

}