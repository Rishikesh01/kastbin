package com.kastbin.service

import com.kastbin.model.AdminModelImpl
import com.kastbin.repository.AdminModelRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

/**
 * @project kastbin
 * @author Rishikesh
 */
@Service
class AdminServiceImpl(private  val adminModelRepo: AdminModelRepo) : UserDetailsService {
    override fun loadUserByUsername(email: String): UserDetails {
        if (isUserPresent(email))
            return AdminModelImpl(adminModelRepo.findByEmail(email)!!)
        throw UsernameNotFoundException("no account with email " + email + "found")
    }

    /**
     * Is user present
     *
     * @param email
     * @return boolean
     */
    fun isUserPresent(email: String): Boolean {
        if (adminModelRepo.findByEmail(email) != null)
            return true
        return false
    }
}