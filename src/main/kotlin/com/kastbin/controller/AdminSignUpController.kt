package com.kastbin.controller

import com.kastbin.configuration.HashingConfig
import com.kastbin.model.AdminModel
import com.kastbin.repository.AdminModelRepo
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

/**
 * @project kastbin
 * @author Rishikesh
 * can only be used by another admin to create a new admin account
 */
@RestController("/admin/signup")
class AdminSignUpController(private val adminModelRepo: AdminModelRepo) {
    @PostMapping
    fun registerAdmin(@RequestBody adminModel: AdminModel) {
        adminModel.password = HashingConfig().hash().encode(adminModel.password)
        adminModelRepo.save(adminModel)
    }
}