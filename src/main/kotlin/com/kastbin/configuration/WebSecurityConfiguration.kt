package com.kastbin.configuration

import com.kastbin.componet.LogoutHandler
import com.kastbin.service.AdminServiceImpl
import com.kastbin.service.OAuth2UserServiceImpl
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.session.SessionRegistry
import org.springframework.security.core.session.SessionRegistryImpl
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint
import org.springframework.security.web.util.matcher.RequestMatcher
import javax.servlet.http.HttpServletRequest


/**
 * Web security configuration
 *
 * @constructor Create empty Web security configuration
 */
@EnableWebSecurity
class WebSecurityConfiguration {
    @Bean
    fun session(): SessionRegistry {
        return SessionRegistryImpl()
    }

    @Configuration
    @Order(3)
    class WebSecurityOAuth(
        @Qualifier("user") private val userDetailsService: UserDetailsService,
        private val oauth: OAuth2UserServiceImpl,
        private val hashingConfig: HashingConfig,
        private val session: SessionRegistry,
        private val logoutHandler: LogoutHandler
    ) : WebSecurityConfigurerAdapter() {

        override fun configure(http: HttpSecurity) {
            http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/oauth").authenticated()
                .and()
                .oauth2Login()
                .userInfoEndpoint()
                .userService(oauth)

            http
                .logout()
                .addLogoutHandler(logoutHandler)
                .and()
                .rememberMe()
                .key("test")

            http.sessionManagement()
                .maximumSessions(5)
                .sessionRegistry(session)
        }

        override fun configure(auth: AuthenticationManagerBuilder) {
            auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(hashingConfig.hash())
        }
    }

    /**
     * Web security basic
     *
     * @property userDetailsService
     * @property hashingConfig
     * @constructor Create empty Web security basic
     */
    @Configuration
    @Order(2)
    class WebSecurityBasic(
        @Qualifier("user") private val userDetailsService: UserDetailsService,
        private val hashingConfig: HashingConfig,
        private val session: SessionRegistry,
        private val logoutHandler: LogoutHandler
    ) : WebSecurityConfigurerAdapter() {

        override fun configure(http: HttpSecurity) {

            http.csrf().disable()
                .antMatcher("/basic/**")
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic()
                .and()
                .logout()
                .addLogoutHandler(logoutHandler)

            http
                .rememberMe()
                .key("test")
                .alwaysRemember(true)

            http.sessionManagement()
                .maximumSessions(5)
                .sessionRegistry(session)
        }

        override fun configure(auth: AuthenticationManagerBuilder) {
            auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(hashingConfig.hash())
        }


    }

    @Configuration
    @Order(1)
    class AdminBasic(
        private val userDetailsService: AdminServiceImpl,
        private val hashingConfig: HashingConfig,
        private val session: SessionRegistry,
        private val logoutHandler: LogoutHandler
    ) : WebSecurityConfigurerAdapter() {

        @Bean
        fun authenticationEntryPoint(): AuthenticationEntryPoint? {
            val entryPoint = BasicAuthenticationEntryPoint()
            entryPoint.realmName = "admin realm"
            return entryPoint
        }

        override fun configure(http: HttpSecurity) {

            http.csrf().disable()
                .antMatcher("/admin/**")
                .authorizeRequests()
                .anyRequest()
                .hasRole("ADMIN")
                .and()
                .httpBasic()
                .and()
                .logout()
                .addLogoutHandler(logoutHandler)

            http
                .rememberMe()
                .key("test")
                .alwaysRemember(true)

            http.sessionManagement()
                .maximumSessions(1)
                .sessionRegistry(session)
        }

        override fun configure(auth: AuthenticationManagerBuilder) {
            auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(hashingConfig.hash())
        }

    }

}