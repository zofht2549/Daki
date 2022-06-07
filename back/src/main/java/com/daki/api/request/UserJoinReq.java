package com.daki.api.request;

import com.daki.db.entity.Oauth;
import com.daki.db.entity.Skin;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
@ApiModel("JoinRequest123123")
public class UserJoinReq {
    @ApiModelProperty(name="이메일(=ID)")
    String email;

    @ApiModelProperty(name="유저 이름")
    String userName;

    @ApiModelProperty(name="닉네임")
    String nickName;

    @ApiModelProperty(name="비밀번호")
    String password;

    @ApiModelProperty(name="생년월일")
    String birth;

    @ApiModelProperty(name="성별")
    boolean gender;

    @ApiModelProperty(name="피부색")
    Skin skin;
//, value = "사이트 회원가입 경우에는 NOT을 넣음 / OAuth2로 회원가입 경우에는 해당 OAuth2를 넣음(GOOGLE / KAKAO)"
    @ApiModelProperty(name = "OAuth2 종류")
    Oauth oauth;

    @Override
    public String toString() {
        return "UserJoinReq{" +
                "email='" + email + '\'' +
                ", userName='" + userName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", password='" + password + '\'' +
                ", birth='" + birth + '\'' +
                ", gender=" + gender +
                ", skin=" + skin +
                ", oAuth2=" + oauth +
                '}';
    }
}
