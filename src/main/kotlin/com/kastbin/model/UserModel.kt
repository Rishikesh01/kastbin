package com.kastbin.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

/**
 * User model
 *
 * @property userName
 * @property email
 * @property pastModel
 * @property oauth
 * @property password
 * @property dateAndTimeOfCreation
 * @property id
 * @constructor Create empty User model
 */
@Document
data class UserModel(
    var userName: String?,
    @Indexed(unique = true)
    var email: String?,
    @DBRef
    var pastModel: MutableList<PastDetailsModel?>?,
    var oauth: Boolean?,
    var password: String?,
    var dateAndTimeOfCreation: LocalDateTime?,
    var isEnabled:Boolean,
    @Id
    var id: Long?
)
