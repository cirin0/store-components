package com.cirin0.storecomponents.config;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

  private BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider provide = new DaoAuthenticationProvider();
    provide.setUserDetailsService(userDetailsService());
    provide.setPasswordEncoder(passwordEncoder());
    return provide;
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf(AbstractHttpConfigurer::disable)
        .headers(headers -> headers
            .frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
        .authorizeHttpRequests(request -> request
            .requestMatchers("/", "/products/**", "/products", "card/**", "/card",
                "/login", "/products/card/add", "products/card/del", "/products/{id}",
                "products/category/{categoryId}", "/category", "/card/del", "/card/add", "/h2-console/**",
                "/auth/**", "/register", "/register-form", "/login-form"
            ).permitAll()
            .requestMatchers("/", "/api/auth/**", "/api/categories", "/api/products").permitAll()
            .requestMatchers("/admin/**", "/api/users/admin/**", "/api/categories/").hasRole("ADMIN")
            .anyRequest().permitAll())
        //.formLogin(Customizer.withDefaults())
        .formLogin(form -> form
            .loginPage("/auth/login")
            .permitAll())
        .httpBasic(AbstractHttpConfigurer::disable)
        //.userDetailsService(userDetailsService)
        .logout(Customizer.withDefaults());

    return http.build();
  }

  @Bean
  public UserDetailsService userDetailsService() {
    UserDetails user = User.withDefaultPasswordEncoder()
        .username("admin")
        .password("admin")
        .roles("ADMIN")
        .build();

    return new InMemoryUserDetailsManager(user);
  }
}
