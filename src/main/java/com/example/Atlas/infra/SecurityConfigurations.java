package com.example.Atlas.infra;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
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

                              .requestMatchers(HttpMethod.POST, "atlas/auth/login").permitAll()
                              .requestMatchers(HttpMethod.POST, "atlas/auth/register/professor").permitAll()
                              .requestMatchers(HttpMethod.POST, "atlas/auth/register/adm").permitAll()


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

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
