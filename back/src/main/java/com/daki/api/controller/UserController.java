package com.daki.api.controller;

import com.daki.api.request.UserJoinReq;
import com.daki.api.request.UserLoginReq;
import com.daki.api.response.BaseRes;
import com.daki.api.response.UserJoinRes;
import com.daki.api.response.UserLoginRes;
import com.daki.api.service.UserService;
import com.daki.common.util.JwtTokenUtil;
import com.daki.db.entity.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/member")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/join")
    @ApiOperation(value = "회원 가입", notes = "회원가입")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공"),
            @ApiResponse(code = 401, message = "인증 실패"),
            @ApiResponse(code = 404, message = "사용자 없음"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<UserJoinRes> register(
            @RequestBody @ApiParam(value="회원가입 정보", required = true) UserJoinReq userJoinReq) {
        System.out.println("================================Enter Controller===============================");
        System.out.println(userJoinReq.toString());
        User user = userService.createUser(userJoinReq);

        return ResponseEntity.status(200).body(UserJoinRes.of(user, userJoinReq.getSkin()));
    }

    @PostMapping("/login")
    @ApiOperation(value = "로그인", notes = "로그인")
    @ApiResponses({
            @ApiResponse(code = 200, message = "성공", response = UserLoginRes.class),
            @ApiResponse(code = 401, message = "인증 실패", response = BaseRes.class),
            @ApiResponse(code = 404, message = "사용자 없음", response = BaseRes.class),
            @ApiResponse(code = 500, message = "서버 오류", response = BaseRes.class)
    })
    public ResponseEntity<UserLoginRes> login(@RequestBody @ApiParam(value="로그인 정보", required = true) UserLoginReq userLoginReq) {
        String userId = userLoginReq.getEmail();
        String password = userLoginReq.getPassword();

        User user = userService.getUserByUserId(userId);

        System.out.println(user.getUserEmail());

        if(user == null)
            return ResponseEntity.status(404).body(UserLoginRes.of(404,"Invalid",null));
        // 로그인 요청한 유저로부터 입력된 패스워드 와 디비에 저장된 유저의 암호화된 패스워드가 같은지 확인.(유효한 패스워드인지 여부 확인)
        if(passwordEncoder.matches(password, user.getUserPassword())) {
            // 유효한 패스워드가 맞는 경우, 로그인 성공으로 응답.(액세스 토큰을 포함하여 응답값 전달)
            return ResponseEntity.ok(UserLoginRes.of(200, "Success", JwtTokenUtil.getToken(userId)));
        }
        // 유효하지 않는 패스워드인 경우, 로그인 실패로 응답.
        return ResponseEntity.status(401).body(UserLoginRes.of(401, "Invalid", null));
    }
    





}
