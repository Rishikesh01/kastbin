package com.kastbin.repository

import com.kastbin.model.AdminModel
import org.springframework.data.mongodb.repository.MongoRepository

/**
 * @project kastbin
 * @author Rishikesh
 */
interface AdminModelRepo : MongoRepository<AdminModel, Long> {
    fun findByEmail(email: String): AdminModel?
}