package com.kastbin.model

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.oauth2.core.user.OAuth2User

/**
 * O auth2user impl
 *
 * @property oauth2User
 * @constructor Create empty O auth2user impl
 */
class OAuth2UserImpl(private val oauth2User: OAuth2User) : OAuth2User {

    override fun getName(): String? {
        return oauth2User.getAttribute<String>("name")
    }

    /**
     * Get email
     *
     * @return
     */
    fun getEmail(): String? {
        return oauth2User.getAttribute<String>("email")
    }

    override fun getAttributes(): MutableMap<String, Any>? {
        return oauth2User.attributes
    }

    /**
     * Get i d
     *
     * @return
     */
    fun getID(): String? {
        return oauth2User.getAttribute("id")
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return oauth2User.authorities
    }

}