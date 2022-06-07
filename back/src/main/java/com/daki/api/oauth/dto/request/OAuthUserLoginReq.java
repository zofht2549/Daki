package com.daki.api.oauth.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OAuthUserLoginReq {
    private String userId;
    private String userPassword;

}
