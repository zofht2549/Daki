package com.daki.api.response;

import java.time.LocalDateTime;

public class BoardListResDto {

    private Long boardNo;
    private String headLine;
    private String title;
    private LocalDateTime registDate;

    public BoardListResDto(Long boardNo, String headLine, String title, LocalDateTime registDate) {
        this.boardNo = boardNo;
        this.headLine = headLine;
        this.title = title;
        this.registDate = registDate;
    }

    public BoardListResDto() {

    }
}
