package com.ovegam.engine.pfmegrnargs.config;

import java.lang.Exception;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class Aws41ApiConfig {
  @Bean
  public SecurityFilterChain resourceServerFilterChain(HttpSecurity httpSecurity) throws Exception {
    return httpSecurity.cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                    httpRequests -> httpRequests.requestMatchers(
                            "/v3/api-docs/**",
                            "/swagger-ui/**",
                            "/swagger-ui.html"
                        ).permitAll()
                        .anyRequest().authenticated())
                .sessionManagement(
                    session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
                .oauth2ResourceServer(
                    oauth2ResourceServer -> oauth2ResourceServer.jwt(Customizer.withDefaults()))
                .build();
  }
}
