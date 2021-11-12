package com.example.practiceproject.data.remote

import com.example.practiceproject.data.remote.dto.User.User
import com.example.practiceproject.data.remote.dto.User.UserItem
import retrofit2.Response
import retrofit2.http.GET

interface UserAPI {

    @GET("users")
    suspend fun getUserData(): Response<ArrayList<UserItem>>
}