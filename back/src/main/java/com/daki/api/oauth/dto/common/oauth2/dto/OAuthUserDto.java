package com.daki.api.oauth.dto.common.oauth2.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OAuthUserDto {
    String userId;
    private String email;
    private String name;
    private String picture;

    @Builder
    public OAuthUserDto(String email, String name, String picture) {
        this.email = email;
        this.name = name;
        this.picture = picture;
    }
}
