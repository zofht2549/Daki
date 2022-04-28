package com.daki.api.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Getter
@ApiModel("UserModifyRequest")
public class UserModifyReq {
    @ApiModelProperty(name="비밀번호")
    String password;

}
