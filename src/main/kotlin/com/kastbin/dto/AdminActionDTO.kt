package com.kastbin.dto

import org.jetbrains.annotations.NotNull

/**
 * @project kastbin
 * @author Rishikesh
 */
data class AdminActionDTO(
    @NotNull
    val email: String?,
    @NotNull
    val action: String?,
    @NotNull
    val reason: String?
)
