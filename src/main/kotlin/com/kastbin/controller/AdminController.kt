package com.kastbin.controller

import com.kastbin.service.AdminServices.TrafficService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user/admin")
class AdminController(private val trafficService:TrafficService){
    @GetMapping
    fun getAllActiveUsers():List<Any>{
        return trafficService.getListOfUsers()
    }

    @PutMapping
    fun action(){

    }

}