package com.example.practiceproject.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.practiceproject.domain.repository.LondonRepository
import com.example.practiceproject.domain.use_case.LoginUseCase

class LoginViewModelFactory(private val useCase: LoginUseCase): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel(useCase) as T
    }
}