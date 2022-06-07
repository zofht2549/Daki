package com.daki.api.request.diary;

import com.daki.db.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DiaryUpdateReq {

    private Long diaryNo;

    private String diaryTitle;

    private String diaryContent;

    private LocalDateTime diaryDate;
}
