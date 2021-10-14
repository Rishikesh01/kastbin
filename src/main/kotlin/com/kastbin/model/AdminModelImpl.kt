package com.kastbin.model

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.UserDetails

/**
 * @project kastbin
 * @author Rishikesh
 */
class AdminModelImpl(val user: AdminModel) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return AuthorityUtils.createAuthorityList("ROLE_ADMIN")
    }

    /**
     * Is o auth
     *
     * @return
     */

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
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}