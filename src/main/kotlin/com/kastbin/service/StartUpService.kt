package com.kastbin.service

import com.kastbin.model.AdminModel
import com.kastbin.repository.AdminModelRepo
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Service

/**
 * @project kastbin
 * @author Rishikesh
 */
@Service
class StartUpService(val adminModelRepo: AdminModelRepo) :CommandLineRunner{
    override fun run(vararg args: String?) {
        val defaultAdmin = AdminModel("admin","admin@kastbin.com","admin",true)
        adminModelRepo.save(defaultAdmin)
    }
}