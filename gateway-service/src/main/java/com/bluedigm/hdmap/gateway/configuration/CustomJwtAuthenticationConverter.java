package com.bluedigm.hdmap.gateway.configuration;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;
import reactor.core.publisher.Mono;

public class CustomJwtAuthenticationConverter implements Converter<Jwt, Mono<? extends AbstractAuthenticationToken>> {

    @Override
    public Mono<? extends AbstractAuthenticationToken> convert(Jwt jwt) {

        System.out.println(jwt);
        return Mono.just(new UsernamePasswordAuthenticationToken(jwt.getId(), ""));
    }
}
