package com.kastbin.service

import com.kastbin.configuration.Bcrypt
import com.kastbin.dto.UserRegistrationDTO
import com.kastbin.mapper.RegistrationDTOMapper
import com.kastbin.repository.UserModelRepo
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserRegistrationService(
    private val registrationMapper: RegistrationDTOMapper,
    private val userRepo: UserModelRepo,
    private val bcrypt: Bcrypt
) {

    fun userRegistration(userDTO: UserRegistrationDTO): Boolean {
        if (EmailValidationService.isEmailValid(userDTO.email!!) &&
            userDTO.password.equals(userDTO.confirmPassword)
        ) {
            val user = registrationMapper.toUserModel(userDTO)
            user.id = UUID.randomUUID().mostSignificantBits
            user.password = bcrypt.hash().encode(user.password)
            userRepo.save(user)
            return true
        }
        return false
    }
}