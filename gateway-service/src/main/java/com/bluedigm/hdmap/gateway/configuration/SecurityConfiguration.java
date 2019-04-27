package com.bluedigm.hdmap.gateway.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfiguration {

    @Bean
    CustomJwtAuthenticationConverter converter() {
        return new CustomJwtAuthenticationConverter();
    }

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(final ServerHttpSecurity http) {
        http
                .csrf().disable()
                .authorizeExchange()
                .pathMatchers("/uaa/**").permitAll()
                .anyExchange().authenticated()
                .and()
                .httpBasic().disable()
                .oauth2ResourceServer()
                .jwt()
//                    .jwtAuthenticationConverter(converter())
        ;

        return http.build();
    }
}
