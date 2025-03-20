package com.example.Atlas.infra;

import com.example.Atlas.repository.AdmRepository;
import com.example.Atlas.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    private securityFilter securityFilter;

          @Bean
          public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
              return http
                      .csrf(csrf -> csrf.disable())
                      .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                      .authorizeHttpRequests(authorize -> authorize

                              .requestMatchers(HttpMethod.POST, "/atlas/auth/login").permitAll()
                              .requestMatchers(HttpMethod.POST, "/atlas/auth/register/professor").permitAll()
                              .requestMatchers(HttpMethod.POST, "/atlas/auth/register/adm").hasRole("ADMINISTRADOR")


                              .requestMatchers(HttpMethod.GET, "/atlas/professor/**").permitAll()
                              .requestMatchers(HttpMethod.POST, "/atlas/professor/**").hasRole("ADMINISTRADOR")
                              .requestMatchers(HttpMethod.DELETE, "/atlas/professor/**").hasRole("ADMINISTRADOR")
                              .requestMatchers(HttpMethod.PUT, "/atlas/professor/**").permitAll()

                              .requestMatchers(HttpMethod.GET, "/atlas/adm/**").hasRole("ADMINISTRADOR")
                              .requestMatchers(HttpMethod.POST, "/atlas/adm/**").hasRole("ADMINISTRADOR")
                              .requestMatchers(HttpMethod.DELETE, "/atlas/adm/**").hasRole("ADMINISTRADOR")
                              .requestMatchers(HttpMethod.PUT, "/atlas/adm/**").hasRole("ADMINISTRADOR")

                              .requestMatchers(HttpMethod.GET, "/atlas/project/**").permitAll()
                              .requestMatchers(HttpMethod.POST, "/atlas/project/**").permitAll()
                              .requestMatchers(HttpMethod.DELETE, "/atlas/project/**").hasRole("ADMINISTRADOR")
                              .requestMatchers(HttpMethod.PUT, "/atlas/project/**").hasRole("ADMINISTRADOR")

                              .anyRequest().authenticated()
                               ) .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                              .build();
          }

    @Bean
    public AuthenticationManager authenticationManager(
            UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);

        return new ProviderManager(authenticationProvider);
    }

    @Bean
    public UserDetailsService userDetailsService(AdmRepository admRepository, ProfessorRepository professorRepository) {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                // Primeiro busca no repositório de administradores
                UserDetails adminUser = admRepository.findByLogin(username);
                if (adminUser != null) {
                    System.out.println("Usuário administrador encontrado: " + username);
                    return adminUser;
                }

                // Se não encontrar, busca no repositório de professores
                UserDetails professorUser = professorRepository.findByLogin(username);
                if (professorUser != null) {
                    System.out.println("Usuário professor encontrado: " + username);
                    return professorUser;
                }

                throw new UsernameNotFoundException("Usuário não encontrado: " + username);
            }
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
