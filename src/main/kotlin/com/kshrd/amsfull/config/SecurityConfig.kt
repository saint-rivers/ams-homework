package com.kshrd.amsfull.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
@EnableWebSecurity
class SecurityConfig {

    @Bean
    fun cors(): CorsConfigurationSource {
        val cors = CorsConfiguration()
        cors.allowedOrigins = listOf("*")
        cors.allowedHeaders = listOf("Access-Control-Allow-Origin")
        val source = UrlBasedCorsConfigurationSource()
        source.setCorsConfigurations(mapOf("/**" to cors))
        return source
    }

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .authorizeHttpRequests {
                it.anyRequest().permitAll()
            }
            .csrf().disable()
            .cors()
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

        return http.build()
    }

}