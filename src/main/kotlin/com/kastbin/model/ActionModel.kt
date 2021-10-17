package com.kastbin.model

import javax.persistence.*

/**
 * @project kastbin
 * @author Rishikesh
 */
@Entity
@Table(name = "action")
data class ActionModel(
    val email: String,
    val action: String,
    val reason: String,
    var admin: String,
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long
)
