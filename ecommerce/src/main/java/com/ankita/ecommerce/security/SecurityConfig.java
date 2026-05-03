package com.ankita.ecommerce.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig {
    @Autowired
    private JwtFilter jwtFilter;

    @Bean
public org.springframework.security.crypto.password.PasswordEncoder passwordEncoder() {
    return new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
}
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
         .csrf(csrf -> csrf.disable())
           .authorizeHttpRequests(auth -> auth
.requestMatchers("/v3/api-docs/**","/v3/api-docs","/swagger-ui/**","/swagger-ui.html",
"/swagger-resources/**",
"/webjars/**").permitAll()
.requestMatchers("/auth/**","/users/register").permitAll()
   .requestMatchers("/products/**").permitAll()     // GET (view product)
.requestMatchers("/products").hasRole("ADMIN") // POST (add product)
    .anyRequest().permitAll()
           )
//.httpBasic(Customizer.withDefaults());
.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
           .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
     }
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer(){
        return (web) -> web.ignoring().requestMatchers("/v3/api-docs/**",
            "/v3/api-docs",
        "/swagger-ui/**",
        "/swagger-ui.html" );
    }
   
}