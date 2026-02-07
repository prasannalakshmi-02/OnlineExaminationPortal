package com.prasanna.OnlineExaminationSystem.config;

import com.prasanna.OnlineExaminationSystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http

                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/css/**", "/js/**", "/images/**").permitAll()
                        .requestMatchers("/admin/**").hasAuthority("ADMIN")   // Matches your UserServiceImpl role
                        .requestMatchers("/student/**").hasAuthority("STUDENT")
                        .anyRequest().authenticated()
                )

                // 3. Login Configuration
                .formLogin(form -> form
                        .loginPage("/login")               // Matches your login.html controller
                        .loginProcessingUrl("/login")      // The URL the form submits to
                        .successHandler((request, response, authentication) -> {
                            // Smart Redirect: Admin -> Admin Dashboard, Student -> Student Dashboard
                            var roles = authentication.getAuthorities();
                            String redirectUrl = "/student/dashboard";
                            for (var role : roles) {
                                if (role.getAuthority().equals("ADMIN")) {
                                    redirectUrl = "/admin/dashboard";
                                }
                            }
                            response.sendRedirect(redirectUrl);
                        })
                        .permitAll()
                )

                // 4. Logout Configuration
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                );

        return http.build();
    }


}
