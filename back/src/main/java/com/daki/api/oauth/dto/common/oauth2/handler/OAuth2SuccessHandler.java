package com.daki.api.oauth.dto.common.oauth2.handler;

import com.daki.api.oauth.dto.common.oauth2.jwt.OAuthJwtToken;
import com.daki.api.oauth.dto.common.oauth2.dto.OAuthUserDto;
import com.daki.api.oauth.dto.common.oauth2.jwt.OAuthJwtUtil;
import com.daki.api.request.UserLoginReq;
import com.daki.api.service.UserService;
import com.daki.common.config.TokenDto;
import com.daki.db.entity.Oauth;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@Component
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final OAuthJwtUtil OAuthJwtUtil;
    private final ObjectMapper objectMapper;

    @Autowired
    @Lazy
    UserService oAuthService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException, ServletException {

        System.out.println("=================================Handler Enter=================================");

        OAuth2User oAuth2User = (OAuth2User)authentication.getPrincipal();
        OAuthUserDto userDto = OAuthUserDto.builder().email("nn@naver").name(oAuth2User.getName()).picture("no").build();
        String oauth2Type = (String) (((OAuth2User) authentication.getPrincipal()).getAttributes().get("type"));
        System.out.println(authentication.toString());



        OAuthJwtToken jwtToken = OAuthJwtUtil.createJwt(authentication);

        response.setContentType("text/html;charset=UTF-8");

        response.addHeader("Auth", jwtToken.getAccessToken());
        response.addHeader("Refresh", jwtToken.getRefreshToken());
        response.setContentType("application/json;charset=UTF-8");

        UserLoginReq loginReq = new UserLoginReq(authentication.getName().toString());
        System.out.println("loginReq.getName : " + authentication.getName().toString());


        System.out.println("loginReq : " + loginReq.toString());

        TokenDto tokenDto = oAuthService.loginUser(
                loginReq,
                Oauth.GOOGLE);

        //OAuth 로그인 성공 + DB에 기존 객체가 없을시 tokenDto = null
        if(tokenDto==null){
            System.out.println("Enter TokenDto is Null");
            getRedirectStrategy().sendRedirect(request, response, "http://localhost:8086/swagger-ui.html?email="+authentication.getName().toString());
        }else{
            System.out.println("Enter TokenDto is Not Null");

            getRedirectStrategy().sendRedirect(request, response, "http://localhost:8086/swagger-ui.html?access_token="+tokenDto.getAccessToken());

        }

    }

}
