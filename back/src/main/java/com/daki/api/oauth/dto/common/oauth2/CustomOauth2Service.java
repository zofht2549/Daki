package com.daki.api.oauth.dto.common.oauth2;

import com.daki.api.oauth.dto.common.oauth2.dto.OAuth2Attribute;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@Service
public class CustomOauth2Service implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        System.out.println("=================================CustomOauth2Service Enter=================================");

        OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService = new DefaultOAuth2UserService();

        OAuth2User oAuth2User = oAuth2UserService.loadUser(userRequest);
        System.out.println(oAuth2User.getName());

        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        System.out.println(registrationId); //facebook

        String userNameAttributeName = userRequest.getClientRegistration()
                .getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        System.out.println(userNameAttributeName); //id

        OAuth2Attribute oAuth2Attribute =
                OAuth2Attribute.of(registrationId, userNameAttributeName, oAuth2User.getAttributes());

        System.out.println(oAuth2Attribute.toString());

        Map<String, Object> memberAttribute = oAuth2Attribute.convertToMap();
        memberAttribute.put("type", registrationId);    //Success Handler에서 OAuth Type을 알게하기 위해 추가

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")),
                memberAttribute, "email");
    }
}
