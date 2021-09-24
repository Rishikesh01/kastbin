package com.kastbin.configuration

import com.kastbin.service.OAuth2UserServiceImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.session.SessionRegistry
import org.springframework.security.core.session.SessionRegistryImpl
import org.springframework.security.core.userdetails.UserDetailsService
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
    class WebSecurityOAuth(
        private val userDetailsService: UserDetailsService,
        private val oauth: OAuth2UserServiceImpl,
        private val hashingConfig: HashingConfig,
        private val session: SessionRegistry
    ) : WebSecurityConfigurerAdapter() {

        override fun configure(http: HttpSecurity) {

            http.csrf().disable()
                .authorizeRequests()
                .mvcMatchers("/", "/past", "/signup").permitAll()
                .mvcMatchers("/oauth/**").authenticated()
                .mvcMatchers("/home/**").authenticated()
                .and()
                .oauth2Login()
                .userInfoEndpoint()
                .userService(oauth)

            http
                .logout()
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
    @Order(0)
    class WebSecurityBasic(
        private val userDetailsService: UserDetailsService,
        private val hashingConfig: HashingConfig,
        private val session: SessionRegistry
    ) : WebSecurityConfigurerAdapter() {

        override fun configure(http: HttpSecurity) {


            http.csrf().disable()
                .requestMatcher(BasicRequestMatcher())
                .authorizeRequests()
                .mvcMatchers("/", "/past", "/signup").permitAll()
                .mvcMatchers("/basic/**").authenticated()
                .antMatchers("/home/**")
                .authenticated()
                .and()
                .httpBasic()
                .and()
                .logout()

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

        /**
         * Basic request matcher
         *
         * @constructor Create empty Basic request matcher
         */
        internal class BasicRequestMatcher : RequestMatcher {
            override fun matches(request: HttpServletRequest): Boolean {
                val auth = request.getHeader("Authorization")
                return auth != null && auth.startsWith("Basic")
            }
        }

    }

}