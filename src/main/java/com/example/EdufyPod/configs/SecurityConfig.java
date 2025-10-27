package com.example.EdufyPod.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

//ED-40-SA
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    //add converter
    //ED-40-SA
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                /*.oauth2ResourceServer(oauth->
                        oauth.jwt(jwt->
                                jwt.jwtAuthenticationConverter(converter)))*/
                .authorizeHttpRequests(auth->
                        auth
                                .anyRequest().permitAll()//change later
                );

        return http.build();
    }
}
