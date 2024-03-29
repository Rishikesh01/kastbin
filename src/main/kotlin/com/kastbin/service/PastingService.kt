package com.kastbin.service

import com.kastbin.dto.PastDTO
import com.kastbin.enums.Type
import com.kastbin.mapper.PastDTOMapper
import com.kastbin.model.OAuth2UserImpl
import com.kastbin.model.PastDetailsModel
import com.kastbin.model.UserDetailsImp
import com.kastbin.model.UserModel
import com.kastbin.repository.PastModelRepo
import com.kastbin.repository.UserModelRepo
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

/**
 * Pasting service
 *
 * @property pastRepo
 * @property userRepo
 * @property pastMapper
 * @property urlGenerator
 * @constructor Create empty Pasting service
 */
@Service
class PastingService(
    private val pastRepo: PastModelRepo,
    private val userRepo: UserModelRepo,
    private val pastMapper: PastDTOMapper,
    private val urlGenerator: StringService
) {

    /**
     * Past
     *  check user is authenticated  and saves the user past appropriately
     * @param pastDTO
     * @param authUser
     * @param basicUser
     * @return
     */
    fun past(pastDTO: PastDTO, authUser: OAuth2User?, basicUser: UserDetailsImp?): String {
        if (authUser != null) {
            val userImpl: OAuth2UserImpl = OAuth2UserImpl(authUser)
            return oauthUserPast(userImpl, pastDTO)
        }
        if (basicUser != null) {
            return basicUserPast(basicUser, pastDTO)
        }
        val pastDetails: PastDetailsModel = pastMapper.toPastDetailsModel(pastDTO)
        if (pastDetails.pastType == null) pastDetails.pastType = Type.TEXT
        pastDetails.user = "Anonymous"
        val url = urlGenerator.randomStringGenerator()
        pastDetails.pastURL = url
        pastDetails.id = UUID.randomUUID().mostSignificantBits
        pastRepo.save(pastDetails)
        return url
    }

    /**
     * OAuthUserPast
     * saves past of OAuth user
     * @param userImpl
     * @param pastDTO
     * @return string
     */
    private fun oauthUserPast(userImpl: OAuth2UserImpl, pastDTO: PastDTO): String {
        val user: UserModel = userRepo.findByEmail(userImpl.getEmail()!!)!!
        val pastDetails: PastDetailsModel = pastMapper.toPastDetailsModel(pastDTO)
        if (pastDetails.pastType == null) pastDetails.pastType = Type.TEXT
        pastDetails.user = user.email
        return pastURL(pastDetails, user)
    }

    /**
     * PastUrl
     *  saves the past
     * @param pastDetails
     * @param user
     * @return
     */
    @Transactional
    protected fun pastURL(pastDetails: PastDetailsModel, user: UserModel): String {
        val url = urlGenerator.randomStringGenerator()
        pastDetails.pastURL = url
        pastDetails.id = UUID.randomUUID().mostSignificantBits
        val past: PastDetailsModel = pastRepo.save(pastDetails)
        user.pastModel = ArrayList()
        (user.pastModel as ArrayList<PastDetailsModel?>).add(past)
        userRepo.save(user)
        pastRepo.save(pastDetails)
        return url
    }

    /**
     * Basic user past
     * save past of no OAuth users
     * @param basicUser
     * @param pastDTO
     * @return
     */
    private fun basicUserPast(basicUser: UserDetailsImp, pastDTO: PastDTO): String {
        val user: UserModel = userRepo.findByEmail(basicUser.getEmail())!!
        val pastDetails: PastDetailsModel = pastMapper.toPastDetailsModel(pastDTO)
        if (pastDetails.pastType == null) pastDetails.pastType = Type.TEXT
        pastDetails.user = basicUser.getEmail()
        return pastURL(pastDetails, user)
    }

}