package com.kastbin.service

import com.kastbin.dto.UserRegistrationDTO
import com.kastbin.mapper.RegistrationDTOMapper
import com.kastbin.repository.UserModelRepo
import org.springframework.stereotype.Service

@Service
class UserRegistrationService(
    private val registrationMapper: RegistrationDTOMapper,
    private val userRepo: UserModelRepo
) {

    fun userRegistration(userDTO: UserRegistrationDTO): Boolean {
        if (EmailValidationService.isEmailValid(userDTO.email!!) &&
            userDTO.password.equals(userDTO.confirmPassword)
        ) {
            userRepo.save(registrationMapper.toUserModel(userDTO))
            return true
        }
        return false
    }
}