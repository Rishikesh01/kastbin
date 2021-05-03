package com.kastbin.dto

import org.jetbrains.annotations.NotNull

data class UserRegistrationDTO(
    @field:NotNull
    var userName: String?,
    @field:NotNull
    var password: String?,
    @field:NotNull
    var confirmPassword: String?,
    @field:NotNull
    var email: String?,
)
