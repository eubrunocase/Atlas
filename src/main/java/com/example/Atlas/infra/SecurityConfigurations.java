package com.example.Atlas.infra;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfigurations {

          @Bean
          public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
              return http
                      .csrf(csrf -> csrf.disable())
                      .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                      .authorizeHttpRequests(authorize -> authorize
                              .requestMatchers(HttpMethod.GET, "atlas/professor/**").permitAll()
                              .requestMatchers(HttpMethod.POST, "atlas/professor/**").permitAll()
                              .requestMatchers(HttpMethod.DELETE, "atlas/professor/**").permitAll()
                              .requestMatchers(HttpMethod.PUT, "atlas/professor/**").permitAll()

                              .requestMatchers(HttpMethod.GET, "atlas/adm/**").permitAll()
                              .requestMatchers(HttpMethod.POST, "atlas/adm/**").permitAll()
                              .requestMatchers(HttpMethod.DELETE, "atlas/adm/**").permitAll()
                              .requestMatchers(HttpMethod.PUT, "atlas/adm/**").permitAll()

                              .requestMatchers(HttpMethod.GET, "atlas/project/**").permitAll()
                              .requestMatchers(HttpMethod.POST, "atlas/project/**").permitAll()
                              .requestMatchers(HttpMethod.DELETE, "atlas/project/**").permitAll()
                              .requestMatchers(HttpMethod.PUT, "atlas/project/**").permitAll()


                              .anyRequest().authenticated())
                              .build();
          }


}
