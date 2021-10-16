package com.kastbin.model

import javax.persistence.*

/**
 * @project kastbin
 * @author Rishikesh
 */
@Entity
data class AdminModel(
    var name: String,
    @Column(unique = true)
    var email: String,
    var password: String,
    var canDeleteAdmin: Boolean,
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long?
)
