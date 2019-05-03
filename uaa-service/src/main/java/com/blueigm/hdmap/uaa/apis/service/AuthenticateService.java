package com.blueigm.hdmap.uaa.apis.service;

import com.blueigm.hdmap.uaa.Exceptions.ApiServiceException;

/**
 * The interface Authenticate service.
 */
public interface AuthenticateService {

    /**
     * Authenticate.
     *
     * @param username    the username
     * @param credentials the credentials
     */
    String authenticate(String username, String credentials) throws ApiServiceException;
}
