package com.kastbin.repository

import com.kastbin.model.PastDetailsModel
import org.springframework.data.mongodb.repository.MongoRepository

interface PastModelRepo : MongoRepository<PastDetailsModel, Long> {
    fun findByPastURL(pastUrl: String): PastDetailsModel?
    fun findByUser(userEmail:String):List<PastDetailsModel?>
}