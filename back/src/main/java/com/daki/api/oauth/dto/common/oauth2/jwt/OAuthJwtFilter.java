package com.daki.api.oauth.dto.common.oauth2.jwt;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.daki.api.oauth.dto.common.oauth2.dto.OAuthUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;


@RequiredArgsConstructor
public class OAuthJwtFilter extends GenericFilterBean{

    private final String TOKEN_PREFIX = "Bearer ";
    private final String HEADER_KEY = "Authorization";
    private final OAuthJwtUtil OAuthJwtUtil;

    // TODO
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        System.out.println("this is JwtFilter");

        HttpServletRequest httpReq = (HttpServletRequest) request;
        String jwtToken = httpReq.getHeader("Auth");
        System.out.println(jwtToken);

        if (jwtToken != null && OAuthJwtUtil.validate(jwtToken)) {
            DecodedJWT decodedJwtToken = OAuthJwtUtil.decodingJwt(jwtToken);

            Claim memberId_claim = decodedJwtToken.getClaim("userId");
            String userId = memberId_claim.asString();

            System.out.println(userId);

            // DB연동을 안했으니 이메일 정보로 유저를 만들어주겠습니다
            OAuthUserDto userDto = OAuthUserDto.builder()
                    .email("nn@naver")
                    .name(userId)
                    .picture("프로필 이미지에요").build();

            Authentication auth = getAuthentication(userDto);
            SecurityContextHolder.getContext().setAuthentication(auth);
        }

//        HttpServletRequest httpReq = (HttpServletRequest) request;
//
//        String jwtToken = httpReq.getHeader(HEADER_KEY);
//
//        DecodedJWT decodedJwtToken = JwtUtil.decodingJwt(jwtToken);
//
//        Claim memberId_claim = decodedJwtToken.getClaim("userId");
//        String userId = memberId_claim.asString();
//
//        System.out.println(userId + " ! good");
//
//        UserDto userDto = UserDto.builder().email("nn@naver").name(userId).picture("no").build();
//
//        Authentication auth = getAuthentication(userDto);
//        SecurityContextHolder.getContext().setAuthentication(auth);

        chain.doFilter(request, response);
    }

    public Authentication getAuthentication(OAuthUserDto userDto) {
        return new UsernamePasswordAuthenticationToken(userDto, "",
                Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
    }

}













