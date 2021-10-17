package com.kastbin.configuration

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

/**
 * Hashing config
 *
 * @constructor Create empty Hashing config
 */
@Service
class HashingConfig {
    /**
     * Hash
     *
     * @return BcryptPasswordEncoder
     */
    fun hash(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }
}