package com.kastbin.service

import org.springframework.stereotype.Service
import java.util.*

/**
 * String service
 *
 * @constructor Create empty String service
 */
@Service
class StringService {
    /**
     * Random string generator
     *
     * @return a random string
     */
    fun randomStringGenerator(): String {
        val leftLimit = 48 // numeral '0'

        val rightLimit = 122 // letter 'z'

        val targetStringLength = 10
        val random = Random()

        val generatedString = random.ints(leftLimit, rightLimit + 1)
            .filter { i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97) }
            .limit(targetStringLength.toLong())
            .collect({ StringBuilder() }, java.lang.StringBuilder::appendCodePoint, java.lang.StringBuilder::append)
            .toString()

        return generatedString
    }
}