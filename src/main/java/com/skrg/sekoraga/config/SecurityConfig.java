package com.skrg.sekoraga.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.header.writers.ReferrerPolicyHeaderWriter.ReferrerPolicy;
import org.springframework.web.filter.CorsFilter;

import com.skrg.sekoraga.security.jwt.JWTFilter;
import com.skrg.sekoraga.security.jwt.TokenProvider;
import com.skrg.sekoraga.service.AuthUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig {

    private final CorsFilter corsFilter;
    private final TokenProvider tokenProvider;
    private final AuthUserDetailsService authUserDetailsService;

    public SecurityConfig(CorsFilter corsFilter, TokenProvider tokenProvider,
            AuthUserDetailsService authUserDetailsService) {
        this.corsFilter = corsFilter;
        this.tokenProvider = tokenProvider;
        this.authUserDetailsService = authUserDetailsService;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter.class)
                .headers(headers -> headers
                        .referrerPolicy(referer -> referer.policy(ReferrerPolicy.STRICT_ORIGIN_WHEN_CROSS_ORIGIN))
                        .frameOptions(frame -> frame.deny()))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth

                        .requestMatchers("/swagger-ui/**").permitAll()
                        .requestMatchers("/swagger-ui.html").permitAll()
                        .requestMatchers("/api-docs/**").permitAll()
                        .requestMatchers("/v3/api-docs/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/attachments/download/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/attachments/upload").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/c-attachments/upload").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/ad-users").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/ad-users/*").permitAll()
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/auth/forgot-password").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/c-exercise-categories/*").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/c-exercise-categories/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/c-exercise-categories").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/c-exercise-videos/*").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/c-exercise-videos/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/c-exercise-videos").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/c-attachments/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/c-activity-schedules/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/c-activity-schedules").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/c-activity-schedules/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/c-activity-schedules/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/c-activity-schedules").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/c-activity-schedules/by-user/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/ad-user-activity-logs/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/ad-user-activity-logs").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/ad-user-activity-logs/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/api/ad-user-activity-logs/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/ad-user-activity-logs").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/ad-users").authenticated()

                        .requestMatchers("/api/**").authenticated())
                .httpBasic(httpBasic -> httpBasic.disable());

        http.addFilterBefore(new JWTFilter(tokenProvider), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider jdbcAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(authUserDetailsService);
        return authenticationProvider;
    }

    @Bean
    @Primary
    public AuthenticationManager defaultAuthenticationBuilder(ObjectPostProcessor<Object> objectPostProcessor)
            throws Exception {
        AuthenticationManagerBuilder auth = new AuthenticationManagerBuilder(objectPostProcessor);
        auth.authenticationProvider(jdbcAuthenticationProvider());
        return auth.build();
    }
}
