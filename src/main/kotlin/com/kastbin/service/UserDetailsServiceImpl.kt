package com.kastbin.service

import com.kastbin.model.UserDetailsImp
import com.kastbin.repository.UserModelRepo
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

/**
 * User details service impl
 *
 * @property userRepo
 * @constructor Create empty User details service impl
 */
@Service
class UserDetailsServiceImpl(private val userRepo: UserModelRepo) : UserDetailsService {
    override fun loadUserByUsername(email: String): UserDetails {
        if (isUserPresent(email))
            return UserDetailsImp(userRepo.findByEmail(email)!!)
        throw UsernameNotFoundException("no account with email " + email + "found")
    }

    /**
     * Is user present
     *
     * @param email
     * @return boolean
     */
    fun isUserPresent(email: String): Boolean {
        if (userRepo.findByEmail(email) != null)
            return true
        return false
    }
}