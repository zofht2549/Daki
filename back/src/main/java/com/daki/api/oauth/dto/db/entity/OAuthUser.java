package com.daki.api.oauth.dto.db.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import javax.persistence.*;

@Entity
@Table(name = "OAuthUser")
@Getter
@NoArgsConstructor
public class OAuthUser extends OAuthBaseEntity {

    @Column(name="user_id")
    private String userId;

    @Column(name="user_password")
    private String userPassword;

    @Column(name = "user_role")
    @Enumerated(EnumType.STRING)
    private OAuthRole userRole;

    @Builder
    public OAuthUser(String userId, String userPassword, OAuthRole userRole){
        Assert.hasText(userId, "id is necessary.");
        Assert.hasText(userPassword, "password is necessary.");

        this.userId = userId;
        this.userPassword = userPassword;
        this.userRole = userRole;
    }

}
