package com.daki.api.service;

import com.daki.api.request.TokenRequestDto;
import com.daki.api.request.UserJoinReq;
import com.daki.api.request.UserLoginReq;
import com.daki.common.config.TokenDto;
import com.daki.db.entity.User;

public interface UserService {
    User createUser(UserJoinReq userJoinReq);
    User getUserByUserId(String email);
    TokenDto loginUser(UserLoginReq userLoginReq);
    User getUserInfo(String email);
    TokenDto reissue(TokenRequestDto tokenRequestDto);
    User getMyInfo();
    User modify(String password);
}
