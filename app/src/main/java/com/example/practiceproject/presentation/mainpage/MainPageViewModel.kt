package com.example.practiceproject.presentation.mainpage

import androidx.lifecycle.ViewModel
import com.example.practiceproject.domain.use_case.LoginUseCase

class MainPageViewModel(private val loginUseCase: LoginUseCase) : ViewModel(){

    fun getSharedPrefName() : String?{
        return loginUseCase.getSharedPrefName()
    }
}