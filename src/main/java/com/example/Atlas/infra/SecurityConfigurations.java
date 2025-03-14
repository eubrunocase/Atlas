package com.example.Atlas.infra;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

          @Bean
          public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
              return http
                      .csrf(csrf -> csrf.disable())
                      .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                      .authorizeHttpRequests(authorize -> authorize
                              .requestMatchers(HttpMethod.POST, "atlas/users/**").permitAll()
                              .requestMatchers(HttpMethod.GET, "atlas/users/**").permitAll()
                              .requestMatchers(HttpMethod.DELETE, "atlas/users/**").permitAll()
                              .requestMatchers(HttpMethod.PUT, "atlas/users/**").permitAll()

                              .requestMatchers(HttpMethod.GET, "atlas/professor/**").permitAll()
                              .requestMatchers(HttpMethod.POST, "atlas/professor/**").permitAll()
                              .requestMatchers(HttpMethod.DELETE, "atlas/professor/**").permitAll()
                              .requestMatchers(HttpMethod.PUT, "atlas/professor/**").permitAll()

                              .requestMatchers(HttpMethod.GET, "atlas/administrador/**").permitAll()
                              .requestMatchers(HttpMethod.POST, "atlas/administrador/**").permitAll()
                              .requestMatchers(HttpMethod.DELETE, "atlas/administrador/**").permitAll()
                              .requestMatchers(HttpMethod.PUT, "atlas/administrador/**").permitAll()

                              .requestMatchers(HttpMethod.GET, "atlas/project/**").permitAll()
                              .requestMatchers(HttpMethod.POST, "atlas/project/**").permitAll()
                              .requestMatchers(HttpMethod.DELETE, "atlas/project/**").permitAll()
                              .requestMatchers(HttpMethod.POST, "atlas/project/**").permitAll()
                              .anyRequest().authenticated())
                      .build();
          }

}
