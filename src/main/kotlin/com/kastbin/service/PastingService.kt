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
import java.util.*

@Service
class PastingService(
    private val pastRepo: PastModelRepo,
    private val userRepo: UserModelRepo,
    private val pastMapper: PastDTOMapper,
    private val urlGenerator: StringService
) {
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
        pastRepo.save(pastDetails)
        return url
    }

    fun oauthUserPast(userImpl: OAuth2UserImpl, pastDTO: PastDTO): String {
        val user: UserModel = userRepo.findAllByEmail(userImpl.getEmail()!!)!!
        val pastDetails: PastDetailsModel = pastMapper.toPastDetailsModel(pastDTO)
        if (pastDetails.pastType == null) pastDetails.pastType = Type.TEXT
        pastDetails.user = user.email
        val url = urlGenerator.randomStringGenerator()
        pastDetails.pastURL = url
        pastDetails.id = UUID.randomUUID().mostSignificantBits
        val past: PastDetailsModel = pastRepo.save(pastDetails)
        user.pastModel?.add(past)
        pastRepo.save(pastDetails)
        return url
    }

    fun basicUserPast(basicUser: UserDetailsImp, pastDTO: PastDTO): String {
        val user: UserModel = userRepo.findAllByEmail(basicUser.getEmail())!!
        val pastDetails: PastDetailsModel = pastMapper.toPastDetailsModel(pastDTO)
        if (pastDetails.pastType == null) pastDetails.pastType = Type.TEXT
        pastDetails.user = basicUser.getEmail()
        val url = urlGenerator.randomStringGenerator()
        pastDetails.pastURL = url
        pastDetails.id = UUID.randomUUID().mostSignificantBits
        val past: PastDetailsModel = pastRepo.save(pastDetails)
        user.pastModel?.add(past)
        pastRepo.save(pastDetails)
        return url
    }
}