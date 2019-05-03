package com.blueigm.hdmap.uaa.apis.controller;

import com.blueigm.hdmap.uaa.Exceptions.ApiServiceException;
import com.blueigm.hdmap.uaa.apis.model.User;
import com.blueigm.hdmap.uaa.apis.service.AuthenticateService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collections;

/**
 * The type Authentication controller.
 */
@RestController
public class AuthenticationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

    private AuthenticateService service;

    /**
     * Token 생성.
     * 사용자 아이디 및 비밀번호를 인증하여 Token 을 반환한다.
     *
     * @param user the user ( username, credentials )
     * @return the response entity
     */
    @PostMapping("/token")
    public ResponseEntity token(@RequestBody User user) {

        LOGGER.info("user : {}", user);

        try {
            String authenticate = service.authenticate(user.getUsername(), user.getCredentials());
            return ResponseEntity.ok().body(Collections.singletonMap("access_token", authenticate));
        } catch (ApiServiceException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Token 생성.
     * 사용자 아이디 및 비밀번호를 인증하여 Token 을 반환한다.
     *
     * @param request the request
     * @return the response entity
     */
    @PostMapping("/head/token")
    public ResponseEntity tokenByHeader(HttpServletRequest request) {

        try {
            String header = request.getHeader("Authorization");
            if (StringUtils.startsWithIgnoreCase(header, "basic ")) {
                String[] decodeHeader = extractAndDecodeHeader(header);

                String authenticate = service.authenticate(decodeHeader[0], decodeHeader[1]);
                return ResponseEntity.ok().body(Collections.singletonMap("access_token", authenticate));
            }
        } catch (ApiServiceException e) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok().build();
    }

    private String[] extractAndDecodeHeader(String header) {

        byte[] base64Token = header.substring(6).getBytes(StandardCharsets.UTF_8);
        byte[] decoded;
        try {
            decoded = Base64.getDecoder().decode(base64Token);
        } catch (IllegalArgumentException e) {
            throw new BadCredentialsException("Failed to decode basic authentication token");
        }

        String token = new String(decoded, StandardCharsets.UTF_8);

        int delim = token.indexOf(':');

        if (delim == -1) {
            throw new BadCredentialsException("Invalid basic authentication token");
        }
        return new String[]{token.substring(0, delim), token.substring(delim + 1)};
    }

    /**
     * Sets service.
     *
     * @param service the service
     */
    @Autowired
    public void setService(AuthenticateService service) {
        this.service = service;
    }
}
