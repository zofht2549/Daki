package com.daki.api.request;

import lombok.Getter;

@Getter
public class TokenRequestDto {
    private String accessToken;
    private String refreshToken;
}
