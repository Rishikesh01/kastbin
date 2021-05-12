package com.kastbin.configuration

import com.kastbin.service.OAuth2UserServiceImpl
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.web.util.matcher.RequestMatcher
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import javax.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus

import javax.servlet.http.HttpServletResponse

import javax.servlet.ServletException

import java.io.IOException

import javax.servlet.FilterChain

import javax.servlet.ServletResponse

import javax.servlet.ServletRequest

import org.springframework.web.filter.GenericFilterBean

@EnableWebSecurity
class WebSecurityConfiguration {

    @Configuration
    class WebSecurityOAuth(
        private val userDetailsService: UserDetailsService,
        private val oauth: OAuth2UserServiceImpl,
        private val bcrypt: Bcrypt
    ) : WebSecurityConfigurerAdapter() {

        override fun configure(http: HttpSecurity) {
            http
                .authorizeRequests()
                .mvcMatchers("/", "/past", "/signup").permitAll()
                .mvcMatchers("/oauth/**").authenticated()
                .and()
                .oauth2Login()
                .userInfoEndpoint()
                .userService(oauth)
        }

        override fun configure(auth: AuthenticationManagerBuilder) {
            auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(bcrypt.hash())
        }
    }

    @Configuration
    @Order(0)
    class WebSecurityBasic(
        private val userDetailsService: UserDetailsService,
        private val bcrypt: Bcrypt
    ) : WebSecurityConfigurerAdapter() {

        override fun configure(http: HttpSecurity) {
            http
                .requestMatcher(BasicRequestMatcher())
                .authorizeRequests()
                .mvcMatchers("/", "/past", "/signup").permitAll()
                .mvcMatchers("/basic/**").authenticated()
                .and()
                .httpBasic()
        }

        override fun configure(auth: AuthenticationManagerBuilder) {
            auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(bcrypt.hash())
        }

        internal class BasicRequestMatcher : RequestMatcher {
            override fun matches(request: HttpServletRequest): Boolean {
                val auth = request.getHeader("Authorization")
                return auth != null && auth.startsWith("Basic")
            }
        }

    }
}