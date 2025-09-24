package com.dealer.hotel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/ca-nhan/**").authenticated()
                .requestMatchers("/dat-phong/**").authenticated()
                .requestMatchers("/**").permitAll()
            )
            .formLogin(form -> form
                .loginPage("/dang-nhap")
                .permitAll()
                .disable()
            )
            .logout(logout -> logout
                .logoutUrl("/dang-xuat")
                .logoutSuccessUrl("/")
                .permitAll()
                .disable()
            )
            .csrf(csrf -> csrf.disable());
        
        return http.build();
    }
}