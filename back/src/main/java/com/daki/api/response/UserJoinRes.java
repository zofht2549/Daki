package com.daki.api.response;


import com.daki.db.entity.Skin;
import com.daki.db.entity.User;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@ApiModel("UserResponse")
@Getter
@Setter
public class UserJoinRes {

    private String email;
    private String userName;
    private String nickName;
    private String password;
    private String birth;
    private boolean gender;
    private Skin skin;

    public static UserJoinRes of(User user, Skin skin) {
        UserJoinRes userJoinRes = new UserJoinRes();

        userJoinRes.setEmail(user.getUserEmail());
        userJoinRes.setUserName(user.getUserName());
        userJoinRes.setNickName(user.getUserNickname());
        userJoinRes.setPassword(user.getUserPassword());
        userJoinRes.setBirth(user.getUserBirth());
        userJoinRes.setGender(user.isUserGender());
        userJoinRes.setSkin(skin);

        return userJoinRes;
    }
}
