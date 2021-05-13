package com.kastbin.repository

import com.kastbin.model.UserModel
import org.springframework.data.mongodb.repository.MongoRepository


interface UserModelRepo : MongoRepository<UserModel, Long> {
    fun findAllByEmail(email: String): UserModel?
}