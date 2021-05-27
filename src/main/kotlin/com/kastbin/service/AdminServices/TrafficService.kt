package com.kastbin.service.AdminServices

import com.kastbin.dto.UserRegistrationDTO
import com.kastbin.model.UserDetailsImp
import org.springframework.security.core.session.SessionRegistry
import org.springframework.stereotype.Service
import java.util.stream.Collector
import kotlin.streams.toList

//@Service
class TrafficService(
    private val session: SessionRegistry
) {

    fun getListOfUsers(): List<UserDetailsImp> {
        val list:List<UserDetailsImp> = session.allPrincipals.stream().filter {
                x -> !session.getAllSessions(x, false).isEmpty()
        }.map {x->x as UserDetailsImp}.toList()

        return  list;
    }
}