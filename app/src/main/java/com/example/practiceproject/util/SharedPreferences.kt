package com.example.practiceproject.util

import android.content.Context

class SharedPreferences(context: Context) {

    private val sharedPreferences = context.getSharedPreferences("PREFERENCE",Context.MODE_PRIVATE)

    fun setName(name : String){
        sharedPreferences.edit().putString("name", name).apply()
    }

    fun getName() : String? {
        return sharedPreferences.getString("name", "")
    }
}