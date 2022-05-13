package com.daki.api.service;

import com.daki.api.request.diary.*;
import com.daki.api.response.diary.*;
import org.json.simple.parser.ParseException;

import java.util.List;

public interface DiaryService {

    DiaryCreateRes createDiary(DiaryCreateReq diaryCreateReq);
    DiaryReadRes readDiary(Long diaryNo);
    DiaryReadAllRes readAllDiary(DiaryReadAllReq diaryReadAllReq);
    DiaryUpdateRes updateDiary(DiaryUpdateReq diaryUpdateReq);
    DiaryDeleteRes deleteDiary(Long diaryNo);

    List<DiaryTitleRes> findTitleByDate(int year, int month);
    List<DiaryTitleRes> findTitleByPageNo(int pageNo);

}
