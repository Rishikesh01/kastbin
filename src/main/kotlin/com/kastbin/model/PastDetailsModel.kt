package com.kastbin.model

import com.kastbin.enums.Type
import java.sql.Date
import javax.persistence.*

/**
 * Past details model
 *
 * @property past
 * @property pastType
 * @property user
 * @property isProctected
 * @property password
 * @property pastURL
 * @property dateOfCreation
 * @property id
 * @constructor Create empty Past details model
 */
@Entity
data class PastDetailsModel(
    var past: String?,
    var pastType: String?,
    var email: String?,
    var isProctected: Boolean?,
    var password: String?,
    @Column(unique = true)
    var pastURL: String?,
    var dateOfCreation: Date?,
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long?
)
