package com.kastbin.model

import javax.persistence.*

/**
 * @project kastbin
 * @author Rishikesh
 */
@Entity
@Table(name = "admin_details")
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
