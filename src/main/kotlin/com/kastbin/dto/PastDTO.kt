package com.kastbin.dto

import com.kastbin.enums.Type
import org.jetbrains.annotations.NotNull


data class PastDTO(
    @field:NotNull
    var past:String?,
    var type:Type?,
    var user:String?,
    var password:String?,
)
