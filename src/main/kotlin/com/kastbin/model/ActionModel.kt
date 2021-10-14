package com.kastbin.model

/**
 * @project kastbin
 * @author Rishikesh
 */
data class ActionModel(
    val userEmail: String,
    val action: String,
    val reason: String,
    var admin: String,
    val id: Long
)
