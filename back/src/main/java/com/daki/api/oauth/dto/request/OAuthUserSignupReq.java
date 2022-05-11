package com.daki.api.oauth.dto.request;

import lombok.*;
import org.springframework.util.Assert;

@Getter
@Setter
@NoArgsConstructor
public class OAuthUserSignupReq {
    private String userId;
    private String userPassword;

    @Builder
    public OAuthUserSignupReq(String userId, String userPassword){
        Assert.hasText(userId, "id is necessary.");
        Assert.hasText(userPassword, "password is necessary.");

        this.userId = userId;
        this.userPassword = userPassword;
    }
}
