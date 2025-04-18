package com.example.Atlas.infra;

import com.example.Atlas.repository.AdmRepository;
import com.example.Atlas.repository.ProfessorRepository;
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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    private final SecurityFilter securityFilter;

    public SecurityConfigurations (SecurityFilter securityFilter) {
        this.securityFilter = securityFilter;
    }

          @Bean
          public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
              return http
                      .csrf(csrf -> csrf.disable())
                      .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                      .authorizeHttpRequests(authorize -> authorize

                                      .requestMatchers( "/swagger-ui/**",
                                              "/v3/api-docs/**",
                                              "/swagger-ui.html").permitAll()

                              .requestMatchers(HttpMethod.POST, "/atlas/auth/login").permitAll()
                              .requestMatchers(HttpMethod.POST, "/atlas/auth/register/professor").permitAll()
                              .requestMatchers(HttpMethod.POST, "/atlas/auth/register/adm").permitAll()


                              .requestMatchers(HttpMethod.GET, "/atlas/professor/**").hasRole("ADMINISTRADOR")
                              .requestMatchers(HttpMethod.POST, "/atlas/professor/**").hasRole("ADMINISTRADOR")
                              .requestMatchers(HttpMethod.DELETE, "/atlas/professor/**").hasRole("ADMINISTRADOR")
                              .requestMatchers(HttpMethod.PUT, "/atlas/professor/**").hasRole("ADMINISTRADOR")

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

//    @Bean
//    public CorsConfigurationSource corsConfigurationSource() {
//        CorsConfig configuration = new CorsConfig();
//
//        configuration.addAllowedOrigin("http://localhost:8081");
//        configuration.addAllowedHeader("*");
//        configuration.addAllowedMethod("*");
//        configuration.setAllowCredentials(true);
//
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/", configuration);
//        return source;
//    }


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
                UserDetails adminUser = admRepository.findByLogin(username);
                if (adminUser != null) {
                    System.out.println("Usuário administrador encontrado: " + username);
                    return adminUser;
                }

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
