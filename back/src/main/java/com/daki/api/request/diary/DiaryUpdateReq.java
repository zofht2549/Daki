package com.daki.api.request.diary;

import com.daki.db.entity.User;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class DiaryUpdateReq {

    private Long diaryNo;

    private String diaryTitle;

    private String diaryContent;
}
