package com.daki.api.oauth.dto.common.oauth2.jwt;

import java.util.Date;
import java.util.stream.Collectors;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.daki.api.oauth.dto.db.repository.OAuthRefreshTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

@Component
public class OAuthJwtUtil {

    private static final String TOKEN_PREFIX = "Bearer ";
    private static final String ISSUER = "simbasns.ml";
    private static final String AUTHORITIES = "auth";
    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 10;
    private static final long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 7;

    private static String secretKey;
    private static Algorithm algo;

    @Autowired
    OAuthRefreshTokenRepository refreshTokenRepository;

    public OAuthJwtUtil(@Value("${jwt.secretKey}") String secretKey) {
        this.secretKey = secretKey;
        this.algo = Algorithm.HMAC512(secretKey);
    }

    public OAuthJwtToken createJwt(Authentication authentication) {

        long nowTime = new Date(System.currentTimeMillis()).getTime();
        Date accessTokenExpireTime = new Date(nowTime + ACCESS_TOKEN_EXPIRE_TIME);
        Date refreshTokenExpireTime = new Date(nowTime + REFRESH_TOKEN_EXPIRE_TIME);

        String roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        String accessJwt =  TOKEN_PREFIX + JWT.create().withIssuer(ISSUER).withExpiresAt(accessTokenExpireTime)
                .withClaim("userId", authentication.getName()).withClaim(AUTHORITIES, roles).sign(algo);

        String refreshJwt = TOKEN_PREFIX + JWT.create().withIssuer(ISSUER).withExpiresAt(refreshTokenExpireTime)
                .sign(algo);

        return OAuthJwtToken.builder().accessToken(accessJwt).refreshToken(refreshJwt).accessTokenExpires(accessTokenExpireTime.getTime()).build();
    }


    public static DecodedJWT decodingJwt(String jwtToken) {
        JWTVerifier jwtverifier = JWT.require(algo).withIssuer(ISSUER).acceptExpiresAt(1).build();

        try {
            DecodedJWT decodedJwt = jwtverifier.verify(jwtToken.replace(TOKEN_PREFIX, ""));

            return decodedJwt;
        } catch (TokenExpiredException e){
            System.err.println("token expired!!");
            DecodedJWT decodedJWT2 = JWT.decode(jwtToken.replace(TOKEN_PREFIX, ""));

            Claim userId_claim = decodedJWT2.getClaim("userId");
            String userId = userId_claim.asString();
            System.out.println(userId + " 은 재발급이 필요해!");

            return decodedJWT2;

        } catch (JWTVerificationException e) {
            System.err.println("not valid jwt token.");
            e.printStackTrace();
        }

        return null;
    }

    public boolean validate(String jwtToken) {
        System.out.println(jwtToken);
        try {
            JWTVerifier jwtVerifier = JWT.require(algo)
                            .withIssuer("simbasns.ml")
                            .acceptExpiresAt(5)
                            .build();

            return true;
        }catch (TokenExpiredException e) {
            System.err.println("alreay expired.");
            return false;
        } catch (JWTVerificationException e) {
            System.err.println("not valid jwt token.");
            return false;
        }
    }

}

