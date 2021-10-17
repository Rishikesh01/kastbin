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
@Table(name = "user_details")
class UserModel(
    var userName: String?,
    @Column(unique = true)
    var email: String?,
    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    @JoinTable(
        name = "user_pasts",
        joinColumns = [JoinColumn(name = "fk_user_email")],
        inverseJoinColumns = [JoinColumn(name = "fk_past_id")]
    )
    var pastModel: MutableList<PastDetailsModel?>? = ArrayList(),
    var oauth: Boolean?,
    var password: String?,
    var dateAndTimeOfCreation: LocalDateTime?,
    var isEnabled: Boolean,
    var isNotLocked: Boolean,
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = 0L
)