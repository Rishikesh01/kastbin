package com.kastbin.model

import com.kastbin.enums.Type
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import java.sql.Date

@Document
data class PastDetailsModel(
    var past: String?,
    var pastType: Type?,
    var user: String?,
    var isProctected: Boolean?,
    var password: String?,
    @Indexed(unique = true)
    var pastURL: String?,
    var dateOfCreation: Date?,
    @Id
    var id: Long?
)
