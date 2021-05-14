package com.kastbin.controller

import com.kastbin.model.OAuth2UserImpl
import com.kastbin.model.UserDetailsImp
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/current")
class CurrentUserController {
    @GetMapping
    fun currentUser(
        @AuthenticationPrincipal user: UserDetailsImp?,
        @AuthenticationPrincipal oauth: OAuth2User?
    ): String? {
        if (user != null) {
            return user.getEmail()
        }
        if (oauth != null) {
            val auth = OAuth2UserImpl(oauth)
            return auth.getEmail()
        }
        return "no user"
    }
}