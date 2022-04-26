package com.daki.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class JwtTokenUtil {
    private static String secretKey;
    private static Integer expirationTime;

//    public static final String TOKEN_PREFIX = "Bearer";
//    public static final String HEADER_STRING = "Authorization";
    public static final String ISSUER = "ssafy.com";

    @Autowired
    public JwtTokenUtil(@Value("${jwt.secret}") String secretKey, @Value("${jwt.expiration}") Integer expirationTime) {
        this.secretKey =  secretKey;
        this.expirationTime = expirationTime;
    }

    public static Date getTokenExpiration(int expirationTime) {
        Date now = new Date();
        return new Date(now.getTime() + expirationTime);
    }

    public static String getToken(String nickName) {
        Date expires = JwtTokenUtil.getTokenExpiration(expirationTime);
        return  JWT.create()
                .withSubject(nickName)
                .withExpiresAt(expires)
                .withIssuer(ISSUER)
                .withIssuedAt(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()))
                .sign(Algorithm.HMAC512(secretKey.getBytes()));
    }
}
