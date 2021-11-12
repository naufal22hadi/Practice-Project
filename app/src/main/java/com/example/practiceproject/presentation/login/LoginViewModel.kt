package com.example.practiceproject.presentation.login

import androidx.lifecycle.*
import com.example.practiceproject.data.database.UserDatabase
import com.example.practiceproject.data.remote.dto.User.UserItem
import com.example.practiceproject.data.remote.dto.UserDataEntity
import com.example.practiceproject.data.remote.dto.Weather.LondonData
import com.example.practiceproject.domain.repository.LondonRepository
import com.example.practiceproject.domain.use_case.LoginUseCase
import kotlinx.coroutines.launch
import retrofit2.Response

class LoginViewModel(private val loginUseCase: LoginUseCase): ViewModel() {

//    private var _myResponse = MutableLiveData<Response<LondonData>>()
//    val myResponse : LiveData<Response<LondonData>> = _myResponse

    private var _userData = MutableLiveData<Response<ArrayList<UserItem>>>()
    val userData : LiveData<Response<ArrayList<UserItem>>> = _userData

    private var _localUserData = MutableLiveData<List<UserDataEntity>>()
    val localUserData : LiveData<List<UserDataEntity>> = _localUserData

//    fun getData() {
//        viewModelScope.launch {
//            val response = loginUseCase.getMyData()
//            _myResponse.value = response
//        }
//    }

    fun getUserData() {
        viewModelScope.launch {
            val apiResponse = loginUseCase.getUserData()
            _userData.value = apiResponse
        }
    }

    suspend fun getLocalDataList() : List<UserDataEntity>{
        return loginUseCase.getUserLocalData()
    }

    fun getLocalDataFromDatabase(){
        viewModelScope.launch {
            val localData = loginUseCase.getUserLocalData()
            _localUserData.value = localData
        }

    }

    fun insertData(userDataEntity: List<UserDataEntity>){
        viewModelScope.launch {
            loginUseCase.insertUserData(userDataEntity)
        }
    }

    fun getDataContent(){
        viewModelScope.launch {
            if (getLocalDataList().isNotEmpty()){
                getLocalDataFromDatabase()
            }else{
                getUserData()
            }
        }

    }

}