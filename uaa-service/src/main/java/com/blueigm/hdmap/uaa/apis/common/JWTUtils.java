package com.blueigm.hdmap.uaa.apis.common;

import com.blueigm.hdmap.uaa.apis.model.User;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;

/**
 * The type Jwt utils.
 */
public class JWTUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(JWTUtils.class);

    private static final String SECRET = "$2a$10$8SWdPbfcj.1Vh/VQDr7jgedOq8h40Fa21z.0WYgfB3kRMhCGPDHvO";

    /**
     * Token string.
     *
     * @param username the username
     * @param vendor   the vendor
     * @return the string
     * @throws JOSEException the jose exception
     */
    public static String token(String username, String vendor) throws JOSEException {
        User user = new User();
        user.setUsername(username);
        user.setVendor(vendor);
        user.setScopes(Arrays.asList("read"));

        return tokenFromUser(user);
    }

    /**
     * Create JWT from user.
     *
     * @param user the user
     * @return the string
     * @throws JOSEException the jose exception
     */
    public static String tokenFromUser(User user) throws JOSEException {

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime plus = now.plus(1, ChronoUnit.HOURS);


        return new JWTUtils.Builder()
                .subject(user.getUsername())
                .issuer("ARDA-ULMO")
                .jwtID(UUID.randomUUID().toString())
                .issueTime(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()))
                .expirationTime(Date.from(plus.atZone(ZoneId.systemDefault()).toInstant()))
                .claim("username", user.getUsername())
                .claim("vendor", user.getVendor())
                .claim("scope", user.getScopes())
                .build();

    }

    /**
     * Validate boolean.
     *
     * @param token the token
     * @return the boolean
     */
    public static boolean validate(String token) {

        try {
            JWSObject parse = JWSObject.parse(token);
            return parse.verify(new MACVerifier(SECRET));
        } catch (ParseException e) {
            LOGGER.error(e.getMessage());
            return false;
        } catch (JOSEException e) {
            LOGGER.error(e.getMessage());
            return false;
        }
    }

    /**
     * The type Builder.
     */
    public static class Builder {

        private JWTClaimsSet.Builder claims = new JWTClaimsSet.Builder();

        /**
         * Instantiates a new Builder.
         */
        public Builder() {}

        /**
         * Build string.
         *
         * @return the string
         * @throws JOSEException the jose exception
         */
        public String build() throws JOSEException {
            JWTClaimsSet build = claims.build();

            JWSHeader header = new JWSHeader.Builder(JWSAlgorithm.HS256)
                    .type(JOSEObjectType.JWT).build();

            JWSObject jwsObject = new JWSObject(header, new Payload(build.toJSONObject()));

            jwsObject.sign(new MACSigner(SECRET));

            return jwsObject.serialize();
        }

        /**
         * Claim jwt utils . builder.
         *
         * @param name  the name
         * @param value the value
         * @return the jwt utils . builder
         */
        public JWTUtils.Builder claim(String name, Object value) {
            claims.claim(name, value);
            return this;
        }

        /**
         * Jwt id jwt utils . builder.
         *
         * @param jti the jti
         * @return the jwt utils . builder
         */
        public JWTUtils.Builder jwtID(String jti) {
            claims.jwtID(jti);
            return this;
        }

        /**
         * Issue time jwt utils . builder.
         *
         * @param iat the iat
         * @return the jwt utils . builder
         */
        public JWTUtils.Builder issueTime(Date iat) {
            claims.issueTime(iat);
            return this;
        }

        /**
         * Expiration time jwt utils . builder.
         *
         * @param exp the exp
         * @return the jwt utils . builder
         */
        public JWTUtils.Builder expirationTime(Date exp) {
            claims.expirationTime(exp);
            return this;
        }

        /**
         * Issuer jwt utils . builder.
         *
         * @param iss the iss
         * @return the jwt utils . builder
         */
        public JWTUtils.Builder issuer(String iss) {
            claims.issuer(iss);
            return this;
        }

        /**
         * Subject jwt utils . builder.
         *
         * @param sub the sub
         * @return the jwt utils . builder
         */
        public JWTUtils.Builder subject(String sub) {
            claims.subject(sub);
            return this;
        }
    }
}


