package com.kastbin.repository

import com.kastbin.model.PastDetailsModel
import org.springframework.data.mongodb.repository.MongoRepository

/**
 * Past model repo
 *
 * @constructor Create empty Past model repo
 */
interface PastModelRepo : MongoRepository<PastDetailsModel, Long> {
    /**
     * Find by past u r l
     *
     * @param pastUrl
     * @return
     */
    fun findByPastURL(pastUrl: String): PastDetailsModel?

    /**
     * Find by user
     *
     * @param userEmail
     * @return
     */
    fun findByUser(userEmail: String): List<PastDetailsModel?>
}