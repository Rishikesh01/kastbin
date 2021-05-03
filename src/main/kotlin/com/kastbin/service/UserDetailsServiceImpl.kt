package com.kastbin.service

import com.kastbin.model.UserDetailsImp
import com.kastbin.repository.UserModelRepo
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl(private val userRepo: UserModelRepo) : UserDetailsService {
    override fun loadUserByUsername(email: String): UserDetails {
        if (isUserPresent(email))
            return UserDetailsImp(userRepo.findAllByEmail(email)!!)
        throw UsernameNotFoundException("no account with email " + email + "found")
    }

    fun isUserPresent(email: String): Boolean {
        if (userRepo.findAllByEmail(email) != null)
            return true
        return false
    }
}