package com.example.practiceproject.data.remote.dto.Weather

data class Sys(
    val country: String,
    val id: Int,
    val sunrise: Int,
    val sunset: Int,
    val type: Int
)