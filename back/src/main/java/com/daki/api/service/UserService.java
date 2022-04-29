package com.daki.api.service;

import com.daki.api.request.*;
import com.daki.common.config.TokenDto;
import com.daki.db.entity.User;

public interface UserService {
    User createUser(UserJoinReq userJoinReq);
    User getUserByUserId(String email);
    TokenDto loginUser(UserLoginReq userLoginReq);
    Boolean checkEmail(String email);
    Boolean checkNickName(String nickName);
    Boolean checkPassword(UserPasswordReq passwordReq);
    TokenDto reissue(TokenRequestDto tokenRequestDto);
    User getMyInfo();
    void modify(UserModifyReq modifyReq);
    void remove();

}
