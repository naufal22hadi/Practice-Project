package com.example.practiceproject.data.remote.dto

import com.example.practiceproject.data.remote.dto.User.UserItem

//class DataTransfer {
    fun UserItem.toUserDataEntity(): UserDataEntity {
        return UserDataEntity(
//            address = address,
//            company = company,
            email = email,
            name = name,
            phone = phone,
            username = username,
            website = website,
            id = id
        )
    }

    fun UserDataEntity.toUserDataItem(): UserItem {
        return UserItem(
            email = email,
            name = name,
            phone = phone,
            username = username,
            website = website,
            id = id
        )
    }
//}