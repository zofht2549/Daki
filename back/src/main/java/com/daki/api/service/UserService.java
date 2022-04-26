package com.daki.api.service;

import com.daki.api.request.UserJoinReq;
import com.daki.db.entity.User;

public interface UserService {
    User createUser(UserJoinReq userJoinReq);
    User getUserByUserId(String email);
}
