package com.kastbin.model

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.UserDetails

class UserDetailsImp(var user:UserModel):UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return AuthorityUtils.createAuthorityList("USER")
    }

    fun isOAuth():Boolean{
        return user.oauth!!
    }

    override fun getPassword(): String {
       return user.password!!
    }

    fun getEmail():String{
        return user.email!!
    }
    override fun getUsername(): String {
       return user.userName!!
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