package com.daki.api.response.diary;

import com.daki.db.entity.Diary;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class DiaryUpdateRes {

    private Long diaryNo;

    private String diaryTitle;

    private String diaryContent;

    private LocalDateTime diaryDate;

    public DiaryUpdateRes(Diary diary) {
        this.diaryNo = diary.getDiaryNo();
        this.diaryTitle = diary.getDiaryTitle();
        this.diaryContent = diary.getDiaryContent();
        this.diaryDate = diary.getDiaryDate();
    }

}
