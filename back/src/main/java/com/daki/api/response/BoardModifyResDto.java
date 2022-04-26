package com.daki.api.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class BoardModifyResDto {

    private Long no;
    private String headLine;
    private String title;
    private String content;
    private LocalDateTime localDateTime;
}
