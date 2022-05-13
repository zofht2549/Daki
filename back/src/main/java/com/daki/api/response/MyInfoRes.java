package com.daki.api.response;


import com.daki.db.entity.User;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

@ApiModel("MyInfoResponse")
@Getter
@Setter
public class MyInfoRes {

    private String email;
    private String nickName;
    private String birth;
    private String gender;
//    private Doll doll;

    public static MyInfoRes of(User user) {
        MyInfoRes myInfoRes = new MyInfoRes();

        myInfoRes.setEmail(user.getUserEmail());
        myInfoRes.setNickName(user.getUserNickname());
        myInfoRes.setBirth(user.getUserBirth());
        myInfoRes.setGender(user.getUserGender());

        return myInfoRes;
    }
}
