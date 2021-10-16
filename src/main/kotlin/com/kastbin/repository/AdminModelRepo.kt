package com.kastbin.repository

import com.kastbin.model.AdminModel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

/**
 * @project kastbin
 * @author Rishikesh
 */
@Repository
interface AdminModelRepo : CrudRepository<AdminModel, Long> {
    fun findByEmail(email: String): AdminModel?
}