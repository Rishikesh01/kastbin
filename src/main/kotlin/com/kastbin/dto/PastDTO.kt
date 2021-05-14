package com.kastbin.dto

import com.fasterxml.jackson.annotation.JsonProperty
import com.kastbin.enums.Type
import org.jetbrains.annotations.NotNull


data class PastDTO(
    @field:NotNull
    var past:String?,
    var type:Type?,
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    var user:String?,
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    var password:String?,
)
