package com.kastbin.model

import java.sql.Date
import javax.persistence.*

/**
 * Past details model
 *
 * @property past
 * @property pastType
 * @property user
 * @property isProtected
 * @property password
 * @property pastURL
 * @property dateOfCreation
 * @property id
 * @constructor Create empty Past details model
 */
@Entity
@Table(name = "past_details")
data class PastDetailsModel(
    var past: String?,
    var pastType: String?,
    var isProtected: Boolean?,
    var password: String?,
    @Column(unique = true)
    var pastURL: String?,
    var dateOfCreation: Date?,
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long?
)
