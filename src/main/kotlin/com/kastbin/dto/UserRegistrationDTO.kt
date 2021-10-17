package com.kastbin.dto

import com.fasterxml.jackson.annotation.JsonProperty
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
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    var password: String?,
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @field:NotNull
    var confirmPassword: String?,
    @field:NotNull
    var email: String?
)
