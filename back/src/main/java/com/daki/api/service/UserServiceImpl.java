package com.daki.api.service;

import com.daki.api.request.*;
import com.daki.common.config.TokenDto;
import com.daki.common.config.TokenProvider;
import com.daki.common.util.RefreshToken;
import com.daki.common.util.RefreshTokenRepository;
import com.daki.common.util.SecurityUtil;
import com.daki.db.entity.Authority;
import com.daki.db.entity.Doll;
import com.daki.db.entity.Oauth;
import com.daki.db.entity.User;
import com.daki.db.repository.DollRepository;
import com.daki.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    DollRepository dollRepository;


    @Transactional
    @Override
    public User createUser(UserJoinReq userJoinReq){
        User user = new User();

        System.out.println("=========================Create User================================");
        System.out.println(userJoinReq.toString());
        System.out.println(userJoinReq.getOauth());

        userJoinReq.getOauth();

        if (userJoinReq.getOauth().equals(Oauth.NOT)){
            user = new User(userJoinReq.getEmail(), userJoinReq.getUserName(), userJoinReq.getNickName(), passwordEncoder.encode(userJoinReq.getPassword()),
                    userJoinReq.getBirth(), userJoinReq.isGender(), 0, Authority.valueOf("ROLE_USER"), userJoinReq.getOauth());
        }else{
            user = new User(userJoinReq.getEmail(), userJoinReq.getUserName(), userJoinReq.getNickName(), passwordEncoder.encode("oauth2"),
                    userJoinReq.getBirth(), userJoinReq.isGender(), 0, Authority.valueOf("ROLE_USER"), userJoinReq.getOauth());
        }

        System.out.println(user.toString());

        User saveUser = userRepository.save(user);  //회원가입 시킴

        Doll doll = new Doll(0, saveUser, userJoinReq.getSkin());
        dollRepository.save(doll);

        return saveUser;
    }

    @Override
    public User getUserByUserId(String email) {
        User user = userRepository.getUserByUserEmail(email);
        return user;
    }

    @Transactional
    @Override
    public TokenDto loginUser(UserLoginReq userLoginReq, Oauth oauth) {
        System.out.println("=======================UserServiceImpl Enter========================");

        if(userRepository.countByUserEmailAndOauth(userLoginReq.getEmail(), oauth)==0){
            System.out.println("이전에 회원가입 한적 없음");
            return null;
        }

        if(oauth == Oauth.GOOGLE){
            userLoginReq.setPassword(passwordEncoder.encode("oauth2"));
        }

        System.out.println("이전에 회원가입 한 적 있음");

        System.out.println("UserLoginReq : "+userLoginReq.toString());

        // 1. Login ID/PW 를 기반으로 AuthenticationToken 생성
        UsernamePasswordAuthenticationToken authenticationToken = userLoginReq.toAuthentication();
        System.out.println(authenticationToken.toString());
        // 2. 실제로 검증 (사용자 비밀번호 체크) 이 이루어지는 부분
        // authenticate 메서드가 실행이 될 때 CustomUserDetailsService 에서 만들었던 loadUserByUsername 메서드가 실행됨
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        // 4. RefreshToken 저장
        RefreshToken refreshToken = RefreshToken.builder()
                .tokenKey(authentication.getName())
                .tokenValue(tokenDto.getRefreshToken())
                .build();

        refreshTokenRepository.save(refreshToken);

        // 5. 토큰 발급
        return tokenDto;
    }

    @Override
    public Boolean checkEmail(String email) {
        boolean findCheck = userRepository.existsByUserEmail(email);
        return findCheck;
    }

    @Override
    public Boolean checkNickName(String nickName) {
        boolean findCheck = userRepository.existsByUserNickname(nickName);
        return findCheck;
    }

    @Override
    public Boolean checkPassword(UserPasswordReq passwordReq) {


        User user = userRepository.findByUserEmail(SecurityUtil.getCurrentUserEmail()).orElseThrow(()
                -> new RuntimeException("로그인 없음"));
//        System.out.println("기존 비밀번호 : " + user.getUserPassword());
//        System.out.println("입력 비밀번호 : " + passwordReq.getMemberPassword() + " => 인코딩 : "+passwordEncoder.encode(passwordReq.getMemberPassword()));
        return passwordEncoder.matches(passwordReq.getMemberPassword(), user.getUserPassword());

    }


    @Transactional
    @Override
    public TokenDto reissue(TokenRequestDto tokenRequestDto) {
        // 1. Refresh Token 검증
        if (!tokenProvider.validateToken(tokenRequestDto.getRefreshToken())) {
            throw new RuntimeException("Refresh Token 이 유효하지 않습니다.");
        }

        // 2. Access Token 에서 Member ID 가져오기
        Authentication authentication = tokenProvider.getAuthentication(tokenRequestDto.getAccessToken());

        // 3. 저장소에서 Member ID 를 기반으로 Refresh Token 값 가져옴
        RefreshToken refreshToken = refreshTokenRepository.findByTokenKey(authentication.getName())
                .orElseThrow(() -> new RuntimeException("로그아웃 된 사용자입니다."));

        // 4. Refresh Token 일치하는지 검사
        if (!refreshToken.getTokenValue().equals(tokenRequestDto.getRefreshToken())) {
            throw new RuntimeException("토큰의 유저 정보가 일치하지 않습니다.");
        }

        // 5. 새로운 토큰 생성
        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        // 6. 저장소 정보 업데이트
        RefreshToken newRefreshToken = refreshToken.updateValue(tokenDto.getRefreshToken());
        refreshTokenRepository.save(newRefreshToken);

        // 토큰 발급
        return tokenDto;
    }

    @Override
    public User getMyInfo() {
//        String str = SecurityUtil.getCurrentUserEmail();
//        System.out.println(str);
        return userRepository.findByUserEmail(SecurityUtil.getCurrentUserEmail()).orElseThrow(()
                -> new RuntimeException("로그인 없음"));
    }

    @Transactional
    @Override
    public void modify(UserModifyReq modifyReq) {
        User user = userRepository.findByUserEmail(SecurityUtil.getCurrentUserEmail()).orElseThrow(()
                -> new RuntimeException("로그인 없음"));
        user.modify(passwordEncoder.encode(modifyReq.getPassword()), modifyReq.getNickName());

        userRepository.save(user);
    }

    @Transactional
    @Override
    public void remove() {
        User user = userRepository.findByUserEmail(SecurityUtil.getCurrentUserEmail()).orElseThrow(()
                -> new RuntimeException("로그인 없음"));
        userRepository.delete(user);
    }

    @Override
    public void Test() {
        System.out.println("==============test=====================");
    }
}
