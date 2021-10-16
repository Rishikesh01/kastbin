package com.kastbin.model

import java.time.LocalDateTime
import javax.persistence.*

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
@Entity
data class UserModel(
    var userName: String?,
    @Column(unique = true)
    var email: String?,
    @OneToMany(cascade = [CascadeType.ALL])
    var pastModel: MutableList<PastDetailsModel?>?,
    var oauth: Boolean?,
    var password: String?,
    var dateAndTimeOfCreation: LocalDateTime?,
    var isEnabled: Boolean,
    var isLocked: Boolean,
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long?
)