package com.daki.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value = "/login/oauth2/code/daki")
@RequiredArgsConstructor
public class KakaoController {

    @GetMapping(value = "/kakao")
    public void kakaoRe(@RequestParam String code){
        System.out.println("====================Enter Kakao Controller====================");
        System.out.println(code);
    }
}
