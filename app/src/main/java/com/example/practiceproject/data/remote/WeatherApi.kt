package com.example.practiceproject.data.remote

import com.example.practiceproject.data.remote.dto.Weather.LondonData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("weather")
    suspend fun getData(
        @Query("q") location : String,
        @Query("appid") id : String
    ) : Response<LondonData>
}