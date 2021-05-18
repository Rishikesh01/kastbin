package com.kastbin.service

import com.kastbin.configuration.HashingConfig
import com.kastbin.dto.UserRegistrationDTO
import com.kastbin.mapper.RegistrationDTOMapper
import com.kastbin.repository.UserModelRepo
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.util.*

/**
 * User registration service
 *
 * @property registrationMapper
 * @property userRepo
 * @property hashingConfig
 * @constructor Create empty User registration service
 */
@Service
class UserRegistrationService(
    private val registrationMapper: RegistrationDTOMapper,
    private val userRepo: UserModelRepo,
    private val hashingConfig: HashingConfig
) {
    /**
     * User registration
     *
     * @param userDTO
     * @return boolean
     */
    @Transactional
    fun userRegistration(userDTO: UserRegistrationDTO): Boolean {
        if (EmailValidationService.isEmailValid(userDTO.email!!) &&
            userDTO.password.equals(userDTO.confirmPassword)
        ) {
            val user = registrationMapper.toUserModel(userDTO)
            user.id = UUID.randomUUID().mostSignificantBits
            user.password = hashingConfig.hash().encode(user.password)
            user.dateAndTimeOfCreation = LocalDateTime.now()
            user.oauth = false
            userRepo.save(user)
            return true
        }
        return false
    }
}