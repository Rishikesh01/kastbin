package com.kastbin.controller

import com.kastbin.dto.UserRegistrationDTO
import com.kastbin.service.UserRegistrationService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Sign up controller
 *
 * @property userRegistrationService
 * @constructor Create empty Sign up controller
 */
@RestController
@RequestMapping("/signup")
class SignUpController(private val userRegistrationService: UserRegistrationService) {
    /**
     * register
     * takes userRegistrationDto from RequestBody
     * @param userRegistrationDTO
     * @return String and Http Status
     */
    @PostMapping
    fun register(@RequestBody userRegistrationDTO: UserRegistrationDTO): ResponseEntity<String> {
        if (userRegistrationService.userRegistration(userRegistrationDTO)) {
            return ResponseEntity<String>("user with registered successfully", HttpStatus.OK)
        }
        return ResponseEntity<String>(
            "user with email ${userRegistrationDTO.email} already exist",
            HttpStatus.BAD_REQUEST
        )
    }
}