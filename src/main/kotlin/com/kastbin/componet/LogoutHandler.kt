package com.kastbin.componet

import com.kastbin.model.UserDetailsImp
import org.springframework.security.core.Authentication
import org.springframework.security.core.session.SessionRegistry
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.security.web.authentication.logout.LogoutHandler
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


/**
 * Logout handler
 *
 * @property session
 * @constructor Create empty Logout handler
 * Expires user session and then handovers the work to SecurityContextLogoutHandler
 * logout method
 */
@Component
class LogoutHandler(private val session: SessionRegistry) : LogoutHandler {
    override fun logout(request: HttpServletRequest?, response: HttpServletResponse?, authentication: Authentication?) {
        if (authentication != null) {
            if (authentication.principal is UserDetailsImp) {
                val user = authentication.principal as UserDetailsImp
                for (information in session.getAllSessions(user, true)) {
                    information.expireNow()
                }
            } else if (authentication.principal is OAuth2User) {
                val user = authentication.principal as OAuth2User
                for (information in session.getAllSessions(user, true)) {
                    information.expireNow()
                }
            }
        }
        SecurityContextLogoutHandler().logout(request, response, authentication)
    }
}