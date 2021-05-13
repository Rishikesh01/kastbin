package com.kastbin.model

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.oauth2.core.user.OAuth2User

class OAuth2UserImpl(private val oauth2User: OAuth2User) : OAuth2User {

    override fun getName(): String? {
        return oauth2User.getAttribute<String>("name")
    }

    fun getEmail(): String? {
        return oauth2User.getAttribute<String>("email")
    }

    override fun getAttributes(): MutableMap<String, Any>? {
        return oauth2User.attributes
    }

    fun getID(): String? {
        return oauth2User.getAttribute("id")
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return oauth2User.authorities
    }

}