package com.daki.api.service;

import com.daki.api.request.UserJoinReq;
import com.daki.api.request.UserLoginReq;
import com.daki.common.config.TokenDto;
import com.daki.db.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class OAuthServiceImpl {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    //true : 이미 회원가입
    //false : 없음. 새로 회원가입
    public TokenDto OAuthLogin(String accessToken, String type){
        System.out.println(accessToken);
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity entity = new HttpEntity(headers);

        Map<String, String> params = new HashMap<String, String>();
        params.put("access_token", accessToken);

        String url;
        switch (type){
            case "kakao" : {url="https://kapi.kakao.com/v2/user/me"; break;}
            case "google" : {url="https://www.googleapis.com/oauth2/v2/userinfo"; break;}
            default: url = "default";
        }

        System.out.println(url);
        url += "?access_token=" + accessToken;

        ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class, params);

        String email = getEmail(response.getBody());

        TokenDto tokenDto = null;

        if(!userRepository.existsByUserEmail(email)){
            System.out.println(1);
            UserJoinReq userJoinReq = new UserJoinReq(email, null, "oauth", null, null, 0);
            userService.createUser(userJoinReq);
        }

        UserLoginReq userLoginReq = new UserLoginReq(email, "oauth");
        tokenDto = userService.loginUser(userLoginReq);

        return tokenDto;
    }

    public String getEmail(Object info){
        String infoString = info.toString();

        String email= null;
        StringTokenizer stringTokenizer = new StringTokenizer(infoString, ", ");
        while(stringTokenizer.hasMoreTokens()){
            String nowStr = stringTokenizer.nextToken();
            if(nowStr.startsWith("email=")){
                email = nowStr;
                break;
            }
        }

        for(int a=0; a<email.length(); a++){
            if(email.charAt(a) == '.'){
                email = email.substring(6, a+4);
                break;
            }
        }

        return email;
    }


}
