package com.daki.api.oauth.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class OAuthUserSignupRes {
    private Long No;
    private String userId;
    private String userPassword;

    @Builder
    public OAuthUserSignupRes(Long No, String userId, String userPassword){
        this.No = No;
        this.userId = userId;
        this.userPassword = userPassword;
    }
}
