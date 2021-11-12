package com.example.practiceproject.data.remote

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.practiceproject.data.remote.dto.UserDataEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(userDataEntity: List<UserDataEntity>)

    @Query("SELECT * FROM user_table")
    suspend fun getData(): List<UserDataEntity>
}