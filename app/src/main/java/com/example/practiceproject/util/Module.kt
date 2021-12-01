package com.example.practiceproject.util

import com.example.practiceproject.data.database.UserDatabase
import com.example.practiceproject.data.remote.RetrofitInstanceUser
import com.example.practiceproject.data.remote.UserAPI
import com.example.practiceproject.domain.repository.LondonRepository
import com.example.practiceproject.domain.use_case.LoginUseCase
import com.example.practiceproject.presentation.login.LoginViewModel
import com.example.practiceproject.presentation.mainpage.MainPageViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module


val viewmodelModule = module {
    viewModel {
        LoginViewModel(get())
    }
    viewModel {
        MainPageViewModel(get())
    }
}

val usecaseModule = module {
    single{
        LoginUseCase(get())
    }
}

val repositoryModule = module {
    single{
        LondonRepository(get(),get())
    }
}

val networkModule = module {
    single {
        RetrofitInstanceUser.createAPI<UserAPI>()
    }
}

val localModule = module {
    single {
        UserDatabase.getDatabase(get()).userDao()
    }
}

val sharedPref = module {
    single {
        SharedPreferences(get())
    }
}

val appModule : List<Module> = listOf(
    viewmodelModule,
    usecaseModule,
    repositoryModule,
    networkModule,
    localModule,
    sharedPref
)