package com.kastbin.repository

import com.kastbin.model.UserModel
import org.springframework.data.mongodb.repository.MongoRepository


/**
 * User model repo
 *
 * @constructor Create empty User model repo
 */
interface UserModelRepo : MongoRepository<UserModel, Long> {
    /**
     * Find by email
     *
     * @param email
     * @return UserModel
     */
    fun findByEmail(email: String): UserModel?
}