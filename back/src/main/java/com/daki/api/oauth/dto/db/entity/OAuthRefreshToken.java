package com.daki.api.oauth.dto.db.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "OAuth_refresh_token")
@Getter
@NoArgsConstructor
public class OAuthRefreshToken extends OAuthBaseEntity {

    @Column(name="token_key")
    private String tokenKey;

    @Column(name="token_value")
    private String tokenValue;

    @Builder
    public OAuthRefreshToken(String tokenKey, String tokenValue){
        this.tokenKey = tokenKey;
        this.tokenValue = tokenValue;
    }
}
