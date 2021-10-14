package com.kastbin.controller

import com.kastbin.dto.AdminActionDTO
import com.kastbin.model.ActionModel
import com.kastbin.model.UserDetailsImp
import com.kastbin.model.domain.CurrentUserTrafficDetails
import com.kastbin.service.adminServices.ActionService
import com.kastbin.service.adminServices.TrafficService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/admin")
class AdminController(
    private val trafficService: TrafficService,
    private val actionService: ActionService
) {
    @GetMapping("/active/users")
    fun getAllActiveUsers(): ResponseEntity<List<CurrentUserTrafficDetails>> {
        return ResponseEntity(trafficService.getListOfUsers(), HttpStatus.OK)
    }

    @PutMapping("/action")
    fun action(
        @AuthenticationPrincipal auth: UserDetailsImp,
        @RequestBody adminActionDTO: AdminActionDTO?
    ): ResponseEntity<String> {
        if (adminActionDTO?.action == null || adminActionDTO.reason == null || adminActionDTO.userEmail == null)
            return ResponseEntity("all the values must be filled before submitting", HttpStatus.BAD_REQUEST)
        else if (actionService.takeAction(adminActionDTO, auth.username))
            return ResponseEntity("submission was successfull", HttpStatus.OK)
        return ResponseEntity(
            "either user with email" + adminActionDTO.userEmail + "does not exist or action performed was invalid",
            HttpStatus.BAD_REQUEST
        )
    }

    @PostMapping("/action/taken/id")
    fun actionTaken(@RequestBody id: Long): ResponseEntity<ActionModel> {
        val action = actionService.getAction(id)
        if (action != null)
            return ResponseEntity(action, HttpStatus.OK)
        return ResponseEntity(HttpStatus.BAD_REQUEST)
    }

}