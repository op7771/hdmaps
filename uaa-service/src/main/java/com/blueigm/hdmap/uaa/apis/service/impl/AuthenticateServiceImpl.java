package com.blueigm.hdmap.uaa.apis.service.impl;

import com.blueigm.hdmap.uaa.Exceptions.ApiServiceException;
import com.blueigm.hdmap.uaa.Exceptions.TokenInvalidException;
import com.blueigm.hdmap.uaa.apis.common.JWTUtils;
import com.blueigm.hdmap.uaa.apis.service.AuthenticateService;
import com.nimbusds.jose.JOSEException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * The type Authenticate service.
 */
@Service
public class AuthenticateServiceImpl implements AuthenticateService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticateServiceImpl.class);

    @Override
    public String authenticate(String username, String credentials) throws ApiServiceException {

        //TODO repository.getUserByUsername(username)

        try {
            return JWTUtils.token(username, "mercedes");
        } catch (JOSEException e) {
            LOGGER.error(e.getMessage());
            throw new TokenInvalidException(e);
        }
    }
}
