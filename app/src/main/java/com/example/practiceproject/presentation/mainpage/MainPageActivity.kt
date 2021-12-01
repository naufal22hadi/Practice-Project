package com.example.practiceproject.presentation.mainpage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.practiceproject.R
import com.example.practiceproject.presentation.login.LoginViewModel
import kotlinx.android.synthetic.main.activity_main_page.*
import org.koin.android.ext.android.inject

class MainPage : AppCompatActivity() {

    private val viewModel by inject<MainPageViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)

        textView.text = viewModel.getSharedPrefName()
    }
}