package com.kastbin.dto

import com.fasterxml.jackson.annotation.JsonProperty
import org.jetbrains.annotations.NotNull


/**
 * Past d t o
 *
 * @property past
 * @property type
 * @property user
 * @property password
 * @constructor Create empty Past d t o
 */
data class PastDTO(
    @field:NotNull
    var past: String?,
    var pastType: String?,
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    var password: String?,
)
