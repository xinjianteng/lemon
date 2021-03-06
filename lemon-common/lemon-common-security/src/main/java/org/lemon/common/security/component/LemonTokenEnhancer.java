package org.lemon.common.security.component;

import org.lemon.common.core.constant.SecurityConstant;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

/**
 * token增强，客户端模式不增强。
 *
 * @author wwc
 */
public class LemonTokenEnhancer implements TokenEnhancer {
    /**
     * Provides an opportunity for customization of an access token (e.g. through its additional information map) during
     * the process of creating a new token for use by a client.
     *
     * @param accessToken    the current access token with its expiration and refresh token
     * @param authentication the current authentication including client and user details
     * @return a new token enhanced with additional information
     */
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        if (SecurityConstant.CLIENT_CREDENTIALS
                .equals(authentication.getOAuth2Request().getGrantType())) {
            return accessToken;
        }

        final Map<String, Object> additionalInfo = new HashMap<>(8);
        User user = (User) authentication.getUserAuthentication().getPrincipal();
        additionalInfo.put(SecurityConstant.DETAILS_USERNAME, user.getUsername());
        additionalInfo.put(SecurityConstant.DETAILS_MOBILE, user.getUsername());
        additionalInfo.put(SecurityConstant.DETAILS_AVATAR, "http://wx.qlogo.cn/mmhead/Q3auHgzwzM7ic2uAs6l4Ug4WVMTIg7KA2o3ic19hicYLIp4lNEUGW5I4Q/");
        additionalInfo.put(SecurityConstant.DETAILS_NICKNAME, "王文城");
        additionalInfo.put(SecurityConstant.DETAILS_LICENSE, SecurityConstant.LEMON_LICENSE);
        additionalInfo.put(SecurityConstant.ACTIVE, Boolean.TRUE);
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);
        return accessToken;
    }
}
