package com.daki.api.service;

import com.daki.api.request.diary.*;
import com.daki.api.response.diary.*;
import com.daki.db.entity.Diary;
import com.daki.db.repository.DiaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiaryServiceImpl implements DiaryService{

    @Autowired
    DiaryRepository diaryRepository;

    @Override
    public DiaryCreateRes createDiary(DiaryCreateReq diaryCreateReq) {
        Diary diary = new Diary(diaryCreateReq.getDiaryTitle(), diaryCreateReq.getDiaryContent(), diaryCreateReq.getDiaryDate(), diaryCreateReq.getUser());

        return new DiaryCreateRes(diaryRepository.save(diary));
    }

    @Override
    public DiaryReadRes readDiary(DiaryReadReq diaryReadReq) {
        return new DiaryReadRes(diaryRepository.getById(diaryReadReq.getDiaryNo()));
    }

    @Override
    public DiaryReadAllRes readAllDiary(DiaryReadAllReq diaryReadAllReq) {
        return null;
    }

    @Override
    public DiaryUpdateRes updateDiary(DiaryUpdateReq diaryUpdateReq) {
        Diary findDiary = diaryRepository.getById(diaryUpdateReq.getDiaryNo());
        Diary updateDiary = new Diary(findDiary.getDiaryNo(), diaryUpdateReq.getDiaryTitle(), diaryUpdateReq.getDiaryContent(), diaryUpdateReq.getDiaryDate(), findDiary.getUser());

        return new DiaryUpdateRes(diaryRepository.save(updateDiary));
    }

    @Override
    public DiaryDeleteRes deleteDiary(DiaryDeleteReq diaryDeleteReq) {
        Diary deleteDiary = diaryRepository.getById(diaryDeleteReq.getDiaryNo());
        diaryRepository.delete(deleteDiary);

        return new DiaryDeleteRes("Delete success.");
    }

}
