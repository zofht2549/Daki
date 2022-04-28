package com.daki.api.controller;

import com.daki.api.request.TokenRequestDto;
import com.daki.api.request.UserJoinReq;
import com.daki.api.request.UserLoginReq;
import com.daki.api.request.UserModifyReq;
import com.daki.api.response.BaseRes;
import com.daki.api.response.MyInfoRes;
import com.daki.api.response.UserJoinRes;
import com.daki.api.response.UserLoginRes;
import com.daki.api.service.UserService;
import com.daki.common.config.TokenDto;
import com.daki.db.entity.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class UserInfoController {

    @Autowired
    UserService userService;

    @GetMapping
    @ApiOperation(value = "내 정보 보기 ", notes = "내 정보 보기")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<MyInfoRes> join() {
        return ResponseEntity.status(200).body(MyInfoRes.of(userService.getMyInfo()));
    }

    @GetMapping("/test")
    public boolean Test(){
        return true;
    }

//    @PatchMapping("/password")
//    @ApiOperation(value = "내 정보 수정", notes = "내 정보 수정")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "성공", response = UserLoginRes.class),
//            @ApiResponse(code = 401, message = "인증 실패", response = BaseRes.class),
//            @ApiResponse(code = 404, message = "사용자 없음", response = BaseRes.class),
//            @ApiResponse(code = 500, message = "서버 오류", response = BaseRes.class)
//    })
//    public ResponseEntity<TokenDto> login(@RequestBody @ApiParam(value="수정 비밀전호", required = true) UserModifyReq modifyReq) {
//        return ResponseEntity.ok(userService.loginUser(userLoginReq));
//    }


}
