package com.example.practiceproject.presentation.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practiceproject.R
import com.example.practiceproject.data.database.UserDatabase
import com.example.practiceproject.data.remote.dto.User.UserItem
import com.example.practiceproject.data.remote.dto.UserDataEntity
import com.example.practiceproject.data.remote.dto.toUserDataEntity
import com.example.practiceproject.data.remote.dto.toUserDataItem
import com.example.practiceproject.domain.repository.LondonRepository
import com.example.practiceproject.domain.use_case.LoginUseCase
import com.example.practiceproject.presentation.adapter.UserAdapter
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.getViewModel

class Login : AppCompatActivity() {

    private val viewModel by inject<LoginViewModel>()
    private lateinit var userAdapter : UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

//        weatherData()
        setupRecyclerview()

        viewModel.getDataContent()
        viewModel.userData.observe(this, Observer { response ->
            if (response.isSuccessful){
                response.body()?.let { its ->
                    userAdapter.setUserData(its)
                    mapData(its)
                }
            }
        })

        viewModel.localUserData.observe(this, Observer {
            userAdapter.setUserData(mapDataItem(it))
        })

    }

    /**
     * This must be removed
     */
//    private suspend fun getData(){
//        if (viewModel.getLocalData().isNotEmpty()){
//            userDataBase()
//        }else{
//            userData()
//        }
//    }

//    private fun weatherData() {
//        val userDao = UserDatabase.getDatabase(application).userDao()
//        val repository = LondonRepository(userDao)
//        val viewModelFactory = LoginViewModelFactory(LoginUseCase(repository))
//        viewModel = ViewModelProvider(this,viewModelFactory).get(LoginViewModel::class.java)
//        viewModel.getData()
//        viewModel.myResponse.observe(this, { response ->
//            textName.text = response.body()?.name
//
//        })
//    }

//    private fun userDataBase() {
//        val userDao = UserDatabase.getDatabase(application).userDao()
//        val repository = LondonRepository(userDao)
//        val viewModelFactory = LoginViewModelFactory(LoginUseCase(repository))
//        viewModel = ViewModelProvider(this, viewModelFactory).get(LoginViewModel::class.java)
//        viewModel.getLocalData()
//        viewModel.localUserData.observe(this, Observer {
//            userAdapter.setUserData(mapDataItem(it))
//        })
//    }

//    private fun userData(){
//        val userDao = UserDatabase.getDatabase(application).userDao()
//        val repository = LondonRepository(userDao)
//        val viewModelFactory = LoginViewModelFactory(LoginUseCase(repository))
//        viewModel = ViewModelProvider(this,viewModelFactory).get(LoginViewModel::class.java)
//        viewModel.getUserData()
//        viewModel.userData.observe(this, Observer {response->
//            if (response.isSuccessful){
//                response.body()?.let { its ->
//                    userAdapter.setUserData(its)
//                    mapData(its)
//
//                }
//            }
//        })
//    }

    private fun setupRecyclerview(){
        userAdapter = UserAdapter()
        userRecyclerview.adapter = userAdapter
        userRecyclerview.layoutManager = LinearLayoutManager(this)
        userRecyclerview.setHasFixedSize(true)
    }

    private fun mapData(userDataItemList : List<UserItem>){
        val userDataEntityList : ArrayList<UserDataEntity> = arrayListOf()
        userDataItemList.forEach{
            userDataEntityList.add(it.toUserDataEntity())
        }
        viewModel.insertData(userDataEntityList)
    }

    private fun mapDataItem(userDataEntityList : List<UserDataEntity>) : ArrayList<UserItem>{
        val userDataItemList : ArrayList<UserItem> = arrayListOf()
        userDataEntityList.forEach{
            userDataItemList.add(it.toUserDataItem())
        }
        return userDataItemList
    }

}