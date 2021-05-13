package com.kastbin.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document
data class UserModel(
    var userName: String?,
    @Indexed(unique = true)
    var email: String?,
    @DBRef
    var pastModel: MutableList<PastDetailsModel?>?,
    var oauth: Boolean?,
    var password: String?,
    var dateAndTimeOfCreation: LocalDateTime?,
    @Id
    var id: Long?
)
