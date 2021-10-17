package com.kastbin.service

import com.kastbin.configuration.HashingConfig
import com.kastbin.dto.UserRegistrationDTO
import com.kastbin.mapper.RegistrationDTOMapper
import com.kastbin.model.UserModel
import com.kastbin.repository.UserModelRepo
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.util.*

/**
 * User services
 *
 * @property registrationMapper
 * @property userRepo
 * @property hashing
 * @constructor Create empty User services
 */
@Service
class UserServices(
    private val registrationMapper: RegistrationDTOMapper,
    private val userRepo: UserModelRepo,
    private val hashing: HashingConfig
) {
    /**
     * User registration
     * @param userDTO
     * @return
     */
    @Transactional
    fun userRegistration(userDTO: UserRegistrationDTO): Boolean {
        if (EmailValidationService.isEmailValid(userDTO.email!!) &&
            userDTO.password.equals(userDTO.confirmPassword) && userRepo.findByEmail(userDTO.email!!) == null
        ) {
            val user = registrationMapper.toUserModel(userDTO)
            user.id = UUID.randomUUID().mostSignificantBits
            user.password = hashing.hash().encode(user.password)
            user.dateAndTimeOfCreation = LocalDateTime.now()
            user.oauth = false
            user.isEnabled = true
            user.isNotLocked = true
            userRepo.save(user)
            return true
        }
        return false
    }

    /**
     * Update user
     *
     * @param userModel
     * @param userReg
     */
    @Transactional
    fun updateUser(userModel: UserModel, userReg: UserRegistrationDTO) {
        userModel.userName = userReg.userName
        userModel.email = userReg.email
        if (userReg.confirmPassword == userReg.password) userModel.password =
            hashing.hash().encode(userReg.password)
        userRepo.save(userModel)
    }

}