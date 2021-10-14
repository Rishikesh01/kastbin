package com.kastbin.controller

import com.kastbin.configuration.HashingConfig
import com.kastbin.dto.UserRegistrationDTO
import com.kastbin.model.AdminModel
import com.kastbin.repository.AdminModelRepo
import com.kastbin.service.UserServices
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

/**
 * Sign up controller
 *
 * @property userServices
 * @constructor Create empty Sign up controller
 */
@RestController
@RequestMapping("/signup")
class SignUpController(private val userServices: UserServices,
private  val adminModelRepo: AdminModelRepo) {
    /**
     * register
     * takes userRegistrationDto from RequestBody
     * @param userRegistrationDTO
     * @return String and Http Status
     */
    @PostMapping
    fun register(@RequestBody userRegistrationDTO: UserRegistrationDTO): ResponseEntity<String> {
        if (userServices.userRegistration(userRegistrationDTO)) {
            return ResponseEntity<String>("user with registered successfully", HttpStatus.OK)
        }
        return ResponseEntity<String>(
            "user with email ${userRegistrationDTO.email} already exist",
            HttpStatus.BAD_REQUEST
        )
    }

    @PostMapping("/admin")
    fun registerAdmin(@RequestBody adminModel: AdminModel ){
      adminModel.password = HashingConfig().hash().encode(adminModel.password)
        adminModel.id = UUID.randomUUID().mostSignificantBits
        adminModelRepo.save(adminModel)
    }
}