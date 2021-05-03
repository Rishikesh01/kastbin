package com.kastbin.repository

import com.kastbin.model.UserModel
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository


interface UserModelRepo:MongoRepository<UserModel,Long> {
    fun findAllByEmail(email:String):UserModel?
}