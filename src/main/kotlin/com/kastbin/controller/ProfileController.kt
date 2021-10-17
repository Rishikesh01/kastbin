package com.kastbin.controller

import com.kastbin.dto.UserRegistrationDTO
import com.kastbin.mapper.RegistrationDTOMapper
import com.kastbin.model.OAuth2UserImpl
import com.kastbin.model.UserDetailsImp
import com.kastbin.model.UserModel
import com.kastbin.repository.UserModelRepo
import com.kastbin.service.UserServices
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.web.bind.annotation.*

/**
 * Profile controller
 *
 * @property userModelRepo
 * @property userServices
 * @constructor Create empty Profile controller
 */
@RestController
@RequestMapping("/user/profile")
class ProfileController(
    private val userModelRepo: UserModelRepo,
    private val userServices: UserServices,
    private val userMapper: RegistrationDTOMapper
) {
    /**
     * Current user details
     *
     * @param user
     * @param oauth
     * @return
     */
    @GetMapping
    fun currentUserDetails(
        @AuthenticationPrincipal user: UserDetailsImp?,
        @AuthenticationPrincipal oauth: OAuth2User?
    ): ResponseEntity<UserRegistrationDTO> {
        if (user != null) {
            val profile = userModelRepo.findByEmail(user.getEmail())?.let { userMapper.toUserRegDTO(it) }
            return ResponseEntity(profile, HttpStatus.OK)
        }
        if (oauth != null) {
            val ouser = OAuth2UserImpl(oauth)
            val profile = userModelRepo.findByEmail(ouser.getEmail()!!)?.let { userMapper.toUserRegDTO(it) }
            return ResponseEntity(profile, HttpStatus.OK)
        }
        return ResponseEntity(HttpStatus.UNAUTHORIZED)
    }

    /**
     * Update current user
     *
     * @param userReg
     * @param user
     * @param oauth
     * @return
     */
    @PutMapping
    fun updateCurrentUser(
        @RequestBody userReg: UserRegistrationDTO,
        @AuthenticationPrincipal user: UserDetailsImp?,
        @AuthenticationPrincipal oauth: OAuth2User?
    ): ResponseEntity<HttpStatus> {
        if (user != null) {
            val userModel: UserModel = userModelRepo.findByEmail(user.getEmail())!!
            userServices.updateUser(userModel, userReg)
            return ResponseEntity(HttpStatus.ACCEPTED)
        } else if (oauth != null) {
            val auth = OAuth2UserImpl(oauth)
            val userModel: UserModel = auth.getEmail()?.let { userModelRepo.findByEmail(it) }!!
            userServices.updateUser(userModel, userReg)
            return ResponseEntity(HttpStatus.ACCEPTED)
        }
        return ResponseEntity(HttpStatus.BAD_REQUEST)
    }

}