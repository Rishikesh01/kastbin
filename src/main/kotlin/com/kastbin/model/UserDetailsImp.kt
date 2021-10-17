package com.kastbin.model

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.UserDetails

/**
 * User details imp
 *
 * @property user
 * @constructor Create empty User details imp
 */
class UserDetailsImp(var user: UserModel) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return AuthorityUtils.createAuthorityList("ROLE_USER")
    }

    /**
     * Is o auth
     *
     * @return
     */
    fun isOAuth(): Boolean {
        return user.oauth!!
    }

    override fun getPassword(): String {
        return user.password!!
    }

    /**
     * Get email
     *
     * @return string
     */
    fun getEmail(): String {
        return user.email!!
    }

    override fun getUsername(): String {
        return user.email!!
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return user.isNotLocked
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return user.isEnabled
    }
}