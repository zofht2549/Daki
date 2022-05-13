package com.daki.api.service;

import com.daki.api.request.diary.*;
import com.daki.api.response.diary.*;
import com.daki.common.util.SecurityUtil;
import com.daki.db.entity.Diary;
import com.daki.db.entity.User;
import com.daki.db.repository.DiaryRepository;
import com.daki.db.repository.UserRepository;
import com.fasterxml.jackson.core.JsonParser;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

//        System.out.println(diaryCreateReq.getContent().toString());
        Diary diary = new Diary(diaryCreateReq.getTitle(), diaryCreateReq.getContent(), user);

        return new DiaryCreateRes(diaryRepository.save(diary));
    }

    @Override
    public DiaryReadRes readDiary(Long diaryNo){
        DiaryReadRes diaryReadRes = new DiaryReadRes(diaryRepository.getById(diaryNo));

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
        Diary updateDiary = new Diary(findDiary.getDiaryNo(), diaryUpdateReq.getTitle(), diaryUpdateReq.getContent().toString(), user);
        return new DiaryUpdateRes(diaryRepository.save(updateDiary));
    }

    @Override
    public DiaryDeleteRes deleteDiary(Long diaryNo) {
        Diary deleteDiary = diaryRepository.getById(diaryNo);
        diaryRepository.delete(deleteDiary);

        return new DiaryDeleteRes("Delete success.");
    }

    @Override
    public List<DiaryTitleRes> findTitleByDate(int year, int month) {
        User user = userRepository.findByUserEmail(SecurityUtil.getCurrentUserEmail()).orElseThrow(()
                -> new RuntimeException("로그인 없음"));

        LocalDateTime startDate = LocalDateTime.of(year, month, 1, 0,0,0);
        LocalDateTime endDate = LocalDateTime.of(year, month+1, 1, 0,0,0);
        List<Diary> diaryList = diaryRepository.findAllByDiaryDateBetweenOrderByDiaryDateAsc(startDate, endDate);
        List<DiaryTitleRes> resList = new ArrayList<>();
        for (Diary diary:diaryList) {
            if(diary.getUser().getUserNo()!=user.getUserNo()) continue;
            DiaryTitleRes res = new DiaryTitleRes(diary);
            resList.add(res);
        }
        return resList;
    }

    @Override
    public List<DiaryTitleRes> findTitleByPageNo(int pageNo) {
        User user = userRepository.findByUserEmail(SecurityUtil.getCurrentUserEmail()).orElseThrow(()
                -> new RuntimeException("로그인 없음"));
        List<Diary> diaryList = new ArrayList<>();
        System.out.println("USER NO : "+user.getUserNo());
        if (pageNo==1){
            System.out.println("Page Start : " + 0 );
            Pageable pageable = PageRequest.of(0,16);
            diaryList = diaryRepository.findAllByUserOrderByDiaryDateDesc(user, pageable);
        }else{
            System.out.println("Page Start : " + (pageNo-1));
            Pageable pageable = (Pageable) PageRequest.of(pageNo-1,16);
            diaryList = diaryRepository.findAllByUserOrderByDiaryDateDesc(user, pageable);
        }


        List<DiaryTitleRes> resList = new ArrayList<>();
        for (Diary diary:diaryList) {
                DiaryTitleRes res = new DiaryTitleRes(diary);
                resList.add(res);
        }

        return resList;
    }

}
