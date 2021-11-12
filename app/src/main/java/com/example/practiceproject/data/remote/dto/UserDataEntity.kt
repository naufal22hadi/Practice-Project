package com.example.practiceproject.data.remote.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class UserDataEntity(

//    val address: Address,
//    val company: Company,

    val email: String,
    val name: String,
    val phone: String,
    val username: String,
    val website: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int
)

