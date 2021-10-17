package com.kastbin.repository

import com.kastbin.model.PastDetailsModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * Past model repo
 *
 * @constructor Create empty Past model repo
 */
@Repository
interface PastModelRepo : JpaRepository<PastDetailsModel, Long> {
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
    //fun findByEmail(userEmail: String): List<PastDetailsModel?>
}