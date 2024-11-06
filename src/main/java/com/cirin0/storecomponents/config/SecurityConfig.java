package com.cirin0.storecomponents.config;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

//  @Bean
//  public AuthenticationProvider authenticationProvider() {
//    DaoAuthenticationProvider provide = new DaoAuthenticationProvider();
//    provide.setUserDetailsService(userDetailsService());
//    provide.setPasswordEncoder(passwordEncoder());
//    return provide;
//  }



  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(request -> request
            .requestMatchers("/", "/auth/login", "auth/register",
                "/api/categories/*", "/api/products/*")
            .permitAll()
            .anyRequest().permitAll())
        .formLogin(form -> form
            .loginPage("/auth/login")
            .defaultSuccessUrl("/")
            .permitAll())
        .logout(logout -> logout
            .logoutSuccessUrl("/auth/login?logout")
            .permitAll());

    return http.build();
  }

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

//  @Bean
//  public UserDetailsService userDetailsService() {
//    UserDetails user = User.withDefaultPasswordEncoder()
//        .username("admin")
//        .password("admin")
//        .roles("ADMIN")
//        .build();
//
//    return new InMemoryUserDetailsManager(user);
//  }
}
