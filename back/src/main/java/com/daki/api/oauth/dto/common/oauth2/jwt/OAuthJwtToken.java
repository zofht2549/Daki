package com.daki.api.oauth.dto.common.oauth2.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OAuthJwtToken {

    private String accessToken;
    private String refreshToken;
    private Long accessTokenExpires;
}
