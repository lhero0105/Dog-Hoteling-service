package com.green.hoteldog.security;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        //세션의 단점 : 메모리에 부담이 크다.
        return httpSecurity.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .httpBasic(http -> http.disable())
                .formLogin(login -> login.disable())
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth.requestMatchers(//세션을 사용하지 않게 세팅.
                                "/api/user/signin"
                                ,"/api/user/nickname-check"
                                ,"/api/user/refresh-token"
                                ,"/api/user/signup"
                                ,"/user/signin"
                                ,"/user/nickname-check"
                                ,"/user/refresh-token"
                                ,"/user/signup"
                                ,"/error"
                                ,"/err"
                                ,"/"
                                ,"/api/email/**"
                                ,"/email/**"
                                ,"/api/board/**"
                                ,"/board/**"
                                ,"/index.html"
                                ,"/static/**"
                                ,"/swagger.html"
                                ,"/swagger-ui/**"
                                ,"/v3/api-docs/**"
                        ).permitAll()
                        .anyRequest()
                        .authenticated()

                ).addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(except ->{except.authenticationEntryPoint(new JwtAuthenticationEntryPoint())
                        .accessDeniedHandler(new JwtAccessDeniedHandler());}).build();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
