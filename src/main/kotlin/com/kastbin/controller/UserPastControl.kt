package com.kastbin.controller

import com.kastbin.dto.PastDTO
import com.kastbin.mapper.PastDTOMapper
import com.kastbin.model.OAuth2UserImpl
import com.kastbin.model.PastDetailsModel
import com.kastbin.model.UserDetailsImp
import com.kastbin.repository.UserModelRepo
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * User controller
 *
 * @property UserModelRepo
 * @property pastDTOMapper
 * @constructor Create empty User controller
 */
@RestController
@RequestMapping("/user/past")
class UserPastControl(
    private val pastDTOMapper: PastDTOMapper,
    private val userRepo: UserModelRepo
) {

    /**
     * User past list
     * gets the user past history based on users email
     * @param oauthUser
     * @param user
     * @return ResponseEntity containing List of pastDTO
     */
    @GetMapping
    fun userPastList(
        @AuthenticationPrincipal oauthUser: OAuth2User?,
        @AuthenticationPrincipal user: UserDetailsImp?
    ): ResponseEntity<List<PastDTO?>> {
        if (oauthUser != null) {
            val oauthUserImpl: OAuth2UserImpl = OAuth2UserImpl(oauthUser)
            val userPast: MutableList<PastDetailsModel?>? = userRepo.findByEmail(oauthUserImpl.getEmail()!!)?.pastModel
            val userPastDto: List<PastDTO>? = userPast?.let { pastDTOMapper.toListPastDTO(it) }
            return ResponseEntity(userPastDto, HttpStatus.OK)
        }
        if (user == null)
            return ResponseEntity(HttpStatus.UNAUTHORIZED)
        val userPast: MutableList<PastDetailsModel?>? = userRepo.findByEmail(user.getEmail())!!.pastModel
        val userPastDto: List<PastDTO>? = userPast?.let { pastDTOMapper.toListPastDTO(it) }
        return ResponseEntity(userPastDto, HttpStatus.OK)
    }
}