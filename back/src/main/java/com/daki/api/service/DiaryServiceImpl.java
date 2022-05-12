package com.daki.api.service;

import com.daki.api.request.diary.*;
import com.daki.api.response.diary.*;
import com.daki.common.util.SecurityUtil;
import com.daki.db.entity.Diary;
import com.daki.db.entity.User;
import com.daki.db.repository.DiaryRepository;
import com.daki.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiaryServiceImpl implements DiaryService{

    @Autowired
    DiaryRepository diaryRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public DiaryCreateRes createDiary(DiaryCreateReq diaryCreateReq) {

        User user = userRepository.findByUserEmail(SecurityUtil.getCurrentUserEmail()).orElseThrow(()
                -> new RuntimeException("토큰 잘못됨"));

        Diary diary = new Diary(diaryCreateReq.getDiaryTitle(), diaryCreateReq.getDiaryContent(), user);

        return new DiaryCreateRes(diaryRepository.save(diary));
    }

    @Override
    public DiaryReadRes readDiary(Long diaryNo) {
        return new DiaryReadRes(diaryRepository.getById(diaryNo));
    }

    @Override
    public DiaryReadAllRes readAllDiary(DiaryReadAllReq diaryReadAllReq) {
        return null;
    }

    @Override
    public DiaryUpdateRes updateDiary(DiaryUpdateReq diaryUpdateReq) {
        Diary findDiary = diaryRepository.getById(diaryUpdateReq.getDiaryNo());
        User user = userRepository.findByUserEmail(SecurityUtil.getCurrentUserEmail()).orElseThrow(()
                -> new RuntimeException("토큰 잘못됨"));
        Diary updateDiary = new Diary(findDiary.getDiaryNo(), diaryUpdateReq.getDiaryTitle(), diaryUpdateReq.getDiaryContent(), user);

        return new DiaryUpdateRes(diaryRepository.save(updateDiary));
    }

    @Override
    public DiaryDeleteRes deleteDiary(Long diaryNo) {
        Diary deleteDiary = diaryRepository.getById(diaryNo);
        diaryRepository.delete(deleteDiary);

        return new DiaryDeleteRes("Delete success.");
    }

}
