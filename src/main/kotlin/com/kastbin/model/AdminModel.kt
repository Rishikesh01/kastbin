package com.kastbin.model

import org.springframework.data.annotation.Id

/**
 * @project kastbin
 * @author Rishikesh
 */
data class AdminModel(
    val name: String,
    val email: String,
    var password: String,
    val canDeleteAdmin: Boolean,
    @Id
    var id: Long?
)
