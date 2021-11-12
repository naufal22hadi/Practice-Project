package com.example.practiceproject.domain.repository

import com.example.practiceproject.data.remote.*
import com.example.practiceproject.data.remote.dto.User.UserItem
import com.example.practiceproject.data.remote.dto.UserDataEntity
import com.example.practiceproject.data.remote.dto.Weather.LondonData
import retrofit2.Response


class LondonRepository(private val userDao : UserDao){

    suspend fun getLondon(): Response<LondonData> {
        return RetrofitInstance.createAPI<WeatherApi>().getData("London","e07d15d038a8ca948410e91d97302548")
    }

    suspend fun getUser(): Response<ArrayList<UserItem>>{
        return RetrofitInstanceUser.createAPI<UserAPI>().getUserData()
    }

    suspend fun insertUserData(userDataEntity: List<UserDataEntity>){
        userDao.insertAll(userDataEntity)
    }

    suspend fun getLocalData(): List<UserDataEntity>{
        return userDao.getData()
    }

}