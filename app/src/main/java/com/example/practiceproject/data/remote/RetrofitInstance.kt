package com.example.practiceproject.data.remote


import com.example.practiceproject.util.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitInstance {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: WeatherApi by lazy {
        retrofit.create(WeatherApi::class.java)
    }

    inline fun <reified I> builder() : I {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(getOKHttp())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        return retrofit.create(I::class.java)
    }

    inline fun <reified I> createAPI() : I {
        return builder()
    }

    fun getOKHttp() : OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(getHTTPLoggingInterceptor(true))
        .connectTimeout(59, TimeUnit.SECONDS)
        .writeTimeout(59, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        .build()

    private fun getHTTPLoggingInterceptor(isDebug : Boolean) : HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = if (isDebug) HttpLoggingInterceptor.Level.BODY
        else HttpLoggingInterceptor.Level.NONE
    }

}