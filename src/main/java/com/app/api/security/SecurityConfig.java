package com.app.api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
	
	@SuppressWarnings("removal")
	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()  
            .authorizeHttpRequests(auth -> auth
            	.requestMatchers("/h2-console/**").permitAll()
                .requestMatchers("/user/**").permitAll() 
                .requestMatchers("/swagger-ui/**").permitAll() 
                .requestMatchers("/v3/api-docs/**").permitAll() 
                .requestMatchers("/swagger-resources/**").permitAll() 
                .anyRequest().authenticated() 
            )
            .headers(headers -> headers.frameOptions().disable()) 
            .httpBasic();  

        return http.build();
    }
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}
}
