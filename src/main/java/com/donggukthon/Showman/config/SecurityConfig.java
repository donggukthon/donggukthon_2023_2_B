package com.donggukthon.Showman.config;

import com.donggukthon.Showman.config.jwt.*;
import com.donggukthon.Showman.config.oauth.CustomOAuth2UserService;
import com.donggukthon.Showman.config.oauth.handler.OAuth2LoginFailureHandler;
import com.donggukthon.Showman.config.oauth.handler.OAuth2LoginSuccessHandler;
import com.donggukthon.Showman.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CorsConfig corsConfig;
    private final JwtService jwtService;
    private final OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;
    private final CustomOAuth2UserService customOAuth2UserService;
    private final OAuth2LoginFailureHandler oAuth2LoginFailureHandler;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final JwtExceptionFilter jwtExceptionFilter;
    private final UserRepository userRepository;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable()) //csrf 비활성
                .formLogin(httpSecurityFormLoginConfigurer -> httpSecurityFormLoginConfigurer.disable()) //폼 로그인 비활성
                .httpBasic(httpSecurityHttpBasicConfigurer -> httpSecurityHttpBasicConfigurer.disable()) //HTTP 기본인증 비활성
                .addFilter(corsConfig.corsFilter())

                .exceptionHandling(exceptionHandlingConfigurer -> exceptionHandlingConfigurer
                        .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                        .accessDeniedHandler(jwtAccessDeniedHandler))

                // 시큐리티가 세션을 만들지도 사용하지도 않음.
                .sessionManagement((sessionManagement) ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                // 특정 URL에 대한 권한 설정
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/success/**").permitAll()
                        .anyRequest().authenticated()
                )

                .oauth2Login(oauth2Login ->
                        oauth2Login
                                .userInfoEndpoint(userInfoEndpoint ->
                                        userInfoEndpoint.userService(customOAuth2UserService))
                                .successHandler(oAuth2LoginSuccessHandler) // 동의하고 계속하기를 눌렀을 때 Handler 설정
                                .failureHandler(oAuth2LoginFailureHandler) // 소셜 로그인 실패 시 핸들러 설정

                );
        // JwtExceptionFilter -> JwtAuthenticationFilter -> UsernamePasswordAuthenticationFilter 순으로 필터가 실행된다.
        return  http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtExceptionFilter, JwtAuthenticationFilter.class).build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(jwtService, userRepository);
        return jwtAuthenticationFilter;
    }
}
