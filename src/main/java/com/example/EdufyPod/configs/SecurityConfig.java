package com.example.EdufyPod.configs;

import com.example.EdufyPod.converters.JwtAuthConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

//ED-40-SA
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)//ED-348-SA
public class SecurityConfig {

    //ED-166-SA
    private JwtAuthConverter jwtAuthConverter;

    //ED-166-SA
    @Autowired
    public SecurityConfig(JwtAuthConverter jwtAuthConverter) {
        this.jwtAuthConverter = jwtAuthConverter;
    }

    //add converter
    //ED-40-SA
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http

                .csrf(csrf->csrf.disable())//ED-120-SA
                //.csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**"))//ED-120-SA
                .authorizeHttpRequests(auth->
                        auth
                                .requestMatchers("/h2-console/**").permitAll()//ED-120-SA
                                //.requestMatchers("/h2-console/**").hasRole("edufy_realm_admin")//ED-120-SA
                                //.requestMatchers("/api/v1/pod/getpodcastbyid/**").permitAll()//ED-76-SA
                                .anyRequest().authenticated()
                                //.anyRequest().permitAll()

                )
                .oauth2ResourceServer(oauth->
                        oauth.jwt(jwt-> jwt.jwtAuthenticationConverter(jwtAuthConverter))) //ED-166-SA
                //ED-120-SA
                .headers(headers ->
                        headers.frameOptions(frame ->
                                frame.disable()))
                .formLogin(Customizer.withDefaults())
                .logout(Customizer.withDefaults());


        return http.build();
    }
}
