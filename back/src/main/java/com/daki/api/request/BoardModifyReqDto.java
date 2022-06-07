package com.daki.api.request;

import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class BoardModifyReqDto {

    private String headLine;
    private String title;
    private String content;


}
