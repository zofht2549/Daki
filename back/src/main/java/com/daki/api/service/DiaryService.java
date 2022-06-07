package com.daki.api.service;

import com.daki.api.request.diary.*;
import com.daki.api.response.diary.*;

public interface DiaryService {

    DiaryCreateRes createDiary(DiaryCreateReq diaryCreateReq);
    DiaryReadRes readDiary(DiaryReadReq diaryReadReq);
    DiaryReadAllRes readAllDiary(DiaryReadAllReq diaryReadAllReq);
    DiaryUpdateRes updateDiary(DiaryUpdateReq diaryUpdateReq);
    DiaryDeleteRes deleteDiary(DiaryDeleteReq diaryDeleteReq);

}
