package com.example.practiceproject.domain.use_case

import com.example.practiceproject.data.remote.dto.User.UserItem
import com.example.practiceproject.data.remote.dto.UserDataEntity
import com.example.practiceproject.data.remote.dto.Weather.LondonData
import com.example.practiceproject.domain.repository.LondonRepository
import retrofit2.Response

class LoginUseCase(private val repository: LondonRepository){

    suspend fun getMyData() : Response<LondonData>{
        return repository.getLondon()
    }
    suspend fun getUserData(): Response<ArrayList<UserItem>>{
        return repository.getUser()
    }
    suspend fun insertUserData(userDataEntity: List<UserDataEntity>){
        repository.insertUserData(userDataEntity)
    }
    suspend fun getUserLocalData() : List<UserDataEntity> {
        return repository.getLocalData()
    }

}