package com.example.practiceproject.presentation.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practiceproject.R
import com.example.practiceproject.data.remote.dto.User.UserItem
import com.example.practiceproject.data.remote.dto.UserDataEntity
import com.example.practiceproject.data.remote.dto.toUserDataEntity
import com.example.practiceproject.data.remote.dto.toUserDataItem
import com.example.practiceproject.presentation.adapter.UserAdapter
import com.example.practiceproject.presentation.mainpage.MainPageActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.android.ext.android.inject

class LoginActivity : AppCompatActivity() {

    private val viewModel by inject<LoginViewModel>()
    private lateinit var userAdapter : UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

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

    private fun setupRecyclerview(){
        userAdapter = UserAdapter(object :UserAdapter.ItemListener{
            override fun userCLicked(memberUser: UserItem) {
                val name = memberUser.name
                viewModel.setSharedPrefName(name)
                val intent = Intent(this@LoginActivity,MainPageActivity::class.java)
                startActivity(intent)
            }

        })
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