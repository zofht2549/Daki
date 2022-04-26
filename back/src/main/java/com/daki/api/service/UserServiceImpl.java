package com.daki.api.service;

import com.daki.api.request.UserJoinReq;
import com.daki.db.entity.Doll;
import com.daki.db.entity.User;
import com.daki.db.repository.DollRepository;
import com.daki.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service("memberService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    DollRepository dollRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public User createUser(UserJoinReq userJoinReq){
        User user = new User(userJoinReq.getEmail(), userJoinReq.getUserName(), userJoinReq.getNickName(), passwordEncoder.encode(userJoinReq.getPassword()),
                userJoinReq.getBirth(), userJoinReq.isGender(), 0);

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
}
