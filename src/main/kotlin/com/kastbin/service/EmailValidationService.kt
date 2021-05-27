package com.kastbin.service

/**
 * Email validation service
 *
 * @constructor Create empty Email validation service
 */

class EmailValidationService {
    companion object {
        @JvmStatic
        val EMAIL_REGEX = "^[A-Za-z](.*)([@])(.+)(\\.)(.+)"
        fun isEmailValid(email: String): Boolean {
            return EMAIL_REGEX.toRegex().matches(email)
        }
    }
}