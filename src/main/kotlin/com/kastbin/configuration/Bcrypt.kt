package com.kastbin.configuration

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class Bcrypt {
    fun hash():BCryptPasswordEncoder{
        return BCryptPasswordEncoder()
    }
}