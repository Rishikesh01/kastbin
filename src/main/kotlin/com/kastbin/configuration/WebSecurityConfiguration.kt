package com.kastbin.configuration

import com.kastbin.service.OAuth2UserServiceImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Configuration
@EnableWebSecurity
class WebSecurityConfiguration(
    private val userDetailsService: UserDetailsService,
    private val oauth: OAuth2UserServiceImpl
) : WebSecurityConfigurerAdapter() {

    @Bean
    fun encrypt(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    override fun configure(http: HttpSecurity) {
        http
            .authorizeRequests()
            .antMatchers("/", "/past")
            .permitAll().anyRequest()
            .authenticated().and()
            .oauth2Login()
            .userInfoEndpoint()
            .userService(oauth)
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth
            .userDetailsService(userDetailsService)
            .passwordEncoder(encrypt())
    }

}