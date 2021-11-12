package com.example.practiceproject.domain.use_case

import com.example.practiceproject.data.remote.WeatherApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Network {

    fun getInterceptor() : OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        return okHttpClient
    }

    fun getMyData() : Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(getInterceptor())
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .build()




    }
    fun getService() = getMyData().create(WeatherApi::class.java)
}