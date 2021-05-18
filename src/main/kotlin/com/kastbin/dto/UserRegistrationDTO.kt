package com.kastbin.dto

import com.kastbin.model.PastDetailsModel
import org.jetbrains.annotations.NotNull

/**
 * User registration d t o
 *
 * @property userName
 * @property password
 * @property confirmPassword
 * @property email
 * @constructor Create empty User registration d t o
 */
data class UserRegistrationDTO(
    @field:NotNull
    var userName: String?,
    @field:NotNull
    var password: String?,
    @field:NotNull
    var confirmPassword: String?,
    @field:NotNull
    var email: String?
)
