package com.daki.api.oauth.dto.common.oauth2.dto;

import java.util.Map;

public abstract class OAuth2UserDto {

    Map<String, Object> attributes;

    public abstract String getUserId();

}
