package com.kastbin.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

/**
 * @project kastbin
 * @author Rishikesh
 */
@Entity
data class ActionModel(
    val email: String,
    val action: String,
    val reason: String,
    var admin: String,
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long
)
