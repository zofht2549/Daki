package com.daki.api.oauth.dto.db.repository;

import com.daki.api.oauth.dto.db.entity.OAuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OAuthUserRepository extends JpaRepository<OAuthUser, Long> {

    Optional<OAuthUser> findByUserId(String userId);
}
