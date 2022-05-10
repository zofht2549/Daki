package com.daki.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
@ApiModel("JoinRequest")
public class UserJoinReq {
    @ApiModelProperty(name="이메일(=ID)")
    String email;

    @ApiModelProperty(name="닉네임")
    String nickName;

    @ApiModelProperty(name="비밀번호")
    String password;

    @ApiModelProperty(name="생년월일")
    String birth;

    @ApiModelProperty(name="성별")
    String userGender;

    @ApiModelProperty(name="캐릭터 종류")
    int dollType;

}
