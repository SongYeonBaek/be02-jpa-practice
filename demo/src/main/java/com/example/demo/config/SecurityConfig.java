package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {

        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http){
        try{
            http.csrf().disable()
                    .authorizeRequests()
                    .antMatchers("/member/login", "/member/signup").permitAll()
                    .anyRequest().denyAll();

            http.formLogin().disable();

            return http.build();

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

}
