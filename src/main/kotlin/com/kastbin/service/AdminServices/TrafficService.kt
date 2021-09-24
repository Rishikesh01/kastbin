package com.kastbin.service.AdminServices

import com.kastbin.model.OAuth2UserImpl
import com.kastbin.model.UserDetailsImp
import com.kastbin.model.UserModel
import com.kastbin.model.domain.CurrentUserTrafficDetails
import com.kastbin.repository.UserModelRepo
import org.springframework.security.core.session.SessionRegistry
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Service
import kotlin.streams.toList

@Service
class TrafficService(
    private val session: SessionRegistry,
    private val userRepo: UserModelRepo
) {

    fun getListOfUsers(): List<CurrentUserTrafficDetails> {
        var list: List<Any> = session.allPrincipals.stream().filter { x ->
            session.getAllSessions(x, false).isNotEmpty()
        }.toList()
        println()
        val newList = ArrayList<CurrentUserTrafficDetails>()
        for (x in list) {
            if (x is UserDetailsImp) {
                val user: UserModel = userRepo.findByEmail(x.getEmail())!!
                val currentUser: CurrentUserTrafficDetails =
                    CurrentUserTrafficDetails(user.userName!!, user.email!!, user.isEnabled)

                newList.add(currentUser)
            } else {
                x as OAuth2User
                val oauth = OAuth2UserImpl(x)
                val user: UserModel = userRepo.findByEmail(oauth.getEmail()!!)!!
                val currentUser: CurrentUserTrafficDetails =
                    CurrentUserTrafficDetails(user.userName!!, user.email!!, user.isEnabled)
                newList.add(currentUser)
            }
        }
        return newList
    }
}