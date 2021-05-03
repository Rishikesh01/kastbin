package com.kastbin.repository

import com.kastbin.model.PastDetailsModel
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.repository.CrudRepository

interface PastModelRepo:MongoRepository<PastDetailsModel,Long> {
    fun findByPastURL(pastUrl:String):PastDetailsModel?
}