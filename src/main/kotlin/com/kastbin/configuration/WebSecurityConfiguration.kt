package com.kastbin.configuration

import com.kastbin.service.OAuth2UserServiceImpl
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService

@EnableWebSecurity
class WebSecurityConfiguration {
    @Configuration
    @Order(1)
    class WebSecurityAuth(
        private val userDetailsService: UserDetailsService,
        private val oauth: OAuth2UserServiceImpl,
        private val bcrypt: Bcrypt
    ) : WebSecurityConfigurerAdapter() {
        override fun configure(http: HttpSecurity) {
            http
                .authorizeRequests()
                .antMatchers("/", "/past", "/signup")
                .permitAll().anyRequest()
                .authenticated().and()
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
    class WebBasicConfig(
        private val userDetailsService: UserDetailsService,
        private val bcrypt: Bcrypt
    ) : WebSecurityConfigurerAdapter() {

        override fun configure(http: HttpSecurity) {
            http
                .authorizeRequests()
                .antMatchers("/", "/past", "/signup", "/Oauth")
                .permitAll().anyRequest()
                .authenticated().and()
                .httpBasic()
        }

        override fun configure(auth: AuthenticationManagerBuilder) {
            auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(bcrypt.hash())
        }

    }
}