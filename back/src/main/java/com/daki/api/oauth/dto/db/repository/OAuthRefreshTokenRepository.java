package com.daki.api.oauth.dto.db.repository;

import com.daki.api.oauth.dto.db.entity.OAuthRefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OAuthRefreshTokenRepository extends JpaRepository<OAuthRefreshToken, Long> {

    OAuthRefreshToken findByTokenKey(String tokenKey);
}
