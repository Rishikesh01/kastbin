package com.kastbin.service

import com.kastbin.configuration.Bcrypt
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
import java.time.LocalDateTime
import kotlin.random.Random

@Service
class OAuth2UserServiceImpl(
    private var userRepo: UserModelRepo,
    private var userMapper: RegistrationDTOMapper,
    private val bcrypt: Bcrypt
) : OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    override fun loadUser(userRequest: OAuth2UserRequest): OAuth2User {

        val user: OAuth2User = DefaultOAuth2UserService().loadUser(userRequest)
        val oauthUser: OAuth2UserImpl = OAuth2UserImpl(user)
        saveUser(oauthUser)
        return user
    }


    fun saveUser(oauthUser: OAuth2UserImpl) {
        if (userRepo.findAllByEmail(oauthUser.getEmail()!!) != null)
            return
        val user: UserModel = userMapper.toUserModel(
            UserRegistrationDTO(
                oauthUser.name,
                "a" + oauthUser.getID().toString(),
                "a" + oauthUser.getID().toString(),
                oauthUser.getEmail()
            )
        )
        user.dateAndTimeOfCreation = LocalDateTime.now()
        user.oauth = true
        user.password = bcrypt.hash().encode(user.password)
        user.id = Random(9).nextLong()
        userRepo.save(user)
    }

}
