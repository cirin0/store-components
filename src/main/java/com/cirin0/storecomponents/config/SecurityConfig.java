package com.cirin0.storecomponents.config;

import com.cirin0.storecomponents.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

  private final CustomUserDetailsService userDetailsService;

  @Bean
  public AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider provide = new DaoAuthenticationProvider();
    provide.setUserDetailsService(userDetailsService);
    provide.setPasswordEncoder(passwordEncoder());
    return provide;
  }


  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf(AbstractHttpConfigurer::disable)
        .httpBasic(Customizer.withDefaults())
        .authorizeHttpRequests(request -> request
            .requestMatchers("/", "/auth/login", "auth/register",
                "/api/categories/*", "/api/products/*")
            .permitAll()
            .requestMatchers("/admin/**").hasRole("ADMIN")
            .anyRequest().permitAll())
        .formLogin(form -> form
            .loginPage("/auth/login")
            .loginProcessingUrl("/auth/login")
            //.defaultSuccessUrl("/users/profile", true)
            .permitAll())
        .logout(logout -> logout
            .logoutSuccessUrl("/auth/login?logout")
            .logoutSuccessUrl("/")
            .permitAll());

    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
