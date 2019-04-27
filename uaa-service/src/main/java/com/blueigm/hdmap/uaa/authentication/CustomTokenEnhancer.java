package com.blueigm.hdmap.uaa.authentication;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Custom token enhancer.
 */
public class CustomTokenEnhancer implements TokenEnhancer {

    /**
     * 클라이언트가 사용할 새로운 토큰을 생성하는 과정에서 액세스 토큰 (예 : 추가 정보 맵을 통해)을 사용자 정의 할 수있는 기회를 제공합니다.
     *
     * @param token the current access token with its expiration and refresh token
     * @param authentication the current authentication including client and user details
     * @return
     */
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken token, OAuth2Authentication authentication) {

        Map<String, Object> additionalInfo = new HashMap<>();
        additionalInfo.put(
                "organization", authentication.getName());
        ((DefaultOAuth2AccessToken) token).setAdditionalInformation(
                additionalInfo);

        return token;
    }
}
