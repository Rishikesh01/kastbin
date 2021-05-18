package com.kastbin.service

import org.springframework.stereotype.Service

/**
 * Email validation service
 *
 * @constructor Create empty Email validation service
 */

class EmailValidationService {
    companion object {
        @JvmStatic
        val EMAIL_REGEX = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})";
        fun isEmailValid(email: String): Boolean {
            return EMAIL_REGEX.toRegex().matches(email)
        }
    }
}