package com.kastbin.service

import com.kastbin.configuration.HashingConfig
import com.kastbin.dto.UserRegistrationDTO
import com.kastbin.mapper.RegistrationDTOMapper
import com.kastbin.model.OAuth2UserImpl
import com.kastbin.model.UserModel
import com.kastbin.repository.UserModelRepo
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

/**
 * O auth2user service impl
 *
 * @property userRepo
 * @property userMapper
 * @property hashingConfig
 * @constructor Create empty O auth2user service impl
 */
@Service
class OAuth2UserServiceImpl(
    private var userRepo: UserModelRepo,
    private var userMapper: RegistrationDTOMapper,
    private val hashingConfig: HashingConfig
) : OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private companion object {
        const val SECRET: String = "TESTING"
    }

    override fun loadUser(userRequest: OAuth2UserRequest): OAuth2User {

        val user: OAuth2User = DefaultOAuth2UserService().loadUser(userRequest)
        val oauthUser: OAuth2UserImpl = OAuth2UserImpl(user)
        saveUser(oauthUser)
        return user
    }

    /**
     * Save user
     *  saves OAuth UserDetails to table
     * @param oauthUser
     */
    @Transactional
    fun saveUser(oauthUser: OAuth2UserImpl) {
        if (userRepo.findByEmail(oauthUser.getEmail()!!) != null)
            return
        val user: UserModel = userMapper.toUserModel(
            UserRegistrationDTO(
                oauthUser.name,
                SECRET + oauthUser.name + oauthUser.getEmail(),
                SECRET + oauthUser.name + oauthUser.getEmail(),
                oauthUser.getEmail()
            )
        )
        user.dateAndTimeOfCreation = LocalDateTime.now()
        user.oauth = true
        user.isEnabled = true
        user.isNotLocked = true
        user.password = hashingConfig.hash().encode(user.password)
        userRepo.save(user)
    }

}
