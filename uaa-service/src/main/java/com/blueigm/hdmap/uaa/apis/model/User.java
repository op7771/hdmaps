package com.blueigm.hdmap.uaa.apis.model;

import com.blueigm.hdmap.uaa.apis.common.Base;

import java.util.List;
import java.util.StringJoiner;

/**
 * The type User.
 */
public class User extends Base {

    private static final long serialVersionUID = 7073782351384320846L;

    private String username;

    private String credentials;

    private String vendor;

    public List<String> scopes;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCredentials() {
        return credentials;
    }

    public void setCredentials(String credentials) {
        this.credentials = credentials;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public List<String> getScopes() {
        return scopes;
    }

    public void setScopes(List<String> scopes) {
        this.scopes = scopes;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
                .add("username='" + username + "'")
                .add("credentials='" + credentials + "'")
                .add("vendor='" + vendor + "'")
                .add("scopes=" + scopes)
                .toString();
    }
}
