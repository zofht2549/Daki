package com.daki.api.oauth.dto.common.oauth2.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;


@RestController
public class FBcontroller {

    String accessToken = "EAAKV3qPnzzcBAOZCXsvcRjgFsUFkF3L27ejfd09zt5YTIborVyt0LZBewGIMsBGTwRw4avZBO5KzptsM2jz8BDc9Ki5f8QHPVyMKKoU1B0OIRhhtLLE1t3ciR76ZBeFOdSioVK8ZAAsvmv4EEHhkqqbL9i6ZC34diLDaqapoxbsfRRzR0T4ZAKes53rugDw4UCqRX3c4ZCkhPHwZBfO4oY02cSQZCA4OD5xnGKRcGqwgUF5gg8coiKsRRRh9s8HbaNfVMZD";

    @GetMapping(value = "/FbId")
    ResponseEntity<String> getId() {
        System.out.println("getId");
        HashMap<String, Object> result = new HashMap<String, Object>();
        String jsonInString = "";

        //TODO : 토큰 발급받을것.

        try {
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders header = new HttpHeaders();
            header.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<?> entity = new HttpEntity<>(header);

            Map<String, String> params = new HashMap<String, String>();
            params.put("access_token", accessToken);

            String url = "https://graph.facebook.com/me";
            url += "?access_token=" + accessToken;
            System.out.println(url);

            UriComponents uri = UriComponentsBuilder.fromHttpUrl(url).build();

            //이 한줄의 코드로 API를 호출해 MAP타입으로 전달 받는다.
            ResponseEntity<Map> resultMap = restTemplate.exchange(uri.toString(), HttpMethod.GET, entity, Map.class, params);
            result.put("statusCode", resultMap.getStatusCodeValue()); //http status code를 확인
            result.put("header", resultMap.getHeaders()); //헤더 정보 확인
            result.put("body", resultMap.getBody()); //실제 데이터 정보 확인

            ObjectMapper mapper = new ObjectMapper();
            jsonInString = mapper.writeValueAsString(resultMap.getBody());
        } catch (Exception e) {
            result.put("statusCode", "500");
            result.put("body"  , "excpetion오류");
            e.printStackTrace();
        }

        return ResponseEntity.ok().body(jsonInString);

    }

    @GetMapping(value = "/Fbfeed")
    ResponseEntity<String> feed(@RequestParam String id) {
        System.out.println("Fbfeed");
        HashMap<String, Object> result = new HashMap<String, Object>();
        String jsonInString = "";

        //TODO : 토큰 발급받을것.
        String accessToken = "EAAKV3qPnzzcBAOZCXsvcRjgFsUFkF3L27ejfd09zt5YTIborVyt0LZBewGIMsBGTwRw4avZBO5KzptsM2jz8BDc9Ki5f8QHPVyMKKoU1B0OIRhhtLLE1t3ciR76ZBeFOdSioVK8ZAAsvmv4EEHhkqqbL9i6ZC34diLDaqapoxbsfRRzR0T4ZAKes53rugDw4UCqRX3c4ZCkhPHwZBfO4oY02cSQZCA4OD5xnGKRcGqwgUF5gg8coiKsRRRh9s8HbaNfVMZD";

        try {
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders header = new HttpHeaders();
            header.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<?> entity = new HttpEntity<>(header);

            Map<String, String> params = new HashMap<String, String>();
            params.put("access_token", accessToken);

            String host = "https://graph.facebook.com";
            String version = "/v13.0/";
            String url = host + version;
            url += id;
            url +="/feed";

            url += "?access_token=" + accessToken;
            url += "&fields=id,full_picture,created_time,from,message";
            System.out.println(url);

            UriComponents uri = UriComponentsBuilder.fromHttpUrl(url).build();

            ResponseEntity<Map> resultMap = restTemplate.exchange(uri.toString(), HttpMethod.GET, entity, Map.class, params);
            result.put("statusCode", resultMap.getStatusCodeValue());
            result.put("header", resultMap.getHeaders());
            result.put("body", resultMap.getBody());

            ObjectMapper mapper = new ObjectMapper();
            jsonInString = mapper.writeValueAsString(resultMap.getBody());
        } catch (Exception e) {
            result.put("statusCode", "500");
            result.put("body"  , "excpetion오류");
            e.printStackTrace();
        }

        return ResponseEntity.ok().body(jsonInString);

    }
}