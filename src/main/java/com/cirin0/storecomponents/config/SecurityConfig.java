package com.cirin0.storecomponents.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;


@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

  private final AuthenticationProvider authenticationProvider;
  private final JwtAuthenticationFilter jwtAuthenticationFilter;

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
        /*
        .formLogin(form -> form
            .loginPage("/auth/login")
            .permitAll())

         */
        .sessionManagement(session -> session
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authenticationProvider(authenticationProvider)
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
        //.httpBasic(AbstractHttpConfigurer::disable)
        //.userDetailsService(userDetailsService)
        .logout(Customizer.withDefaults());

    return http.build();
  }

  @Bean
  CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration configuration = new CorsConfiguration();

    configuration.setAllowedOrigins(List.of("http://localhost:3000"));
    configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
    configuration.setAllowCredentials(true);
    configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", configuration);

    return source;
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
