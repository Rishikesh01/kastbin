package com.kastbin.repository

import com.kastbin.model.UserModel
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository


/**
 * User model repo
 *
 * @constructor Create empty User model repo
 */
@Repository
interface UserModelRepo : CrudRepository<UserModel, Long> {
    /**
     * Find by email
     *
     * @param email
     * @return UserModel
     */
    fun findByEmail(email: String): UserModel?
}