package com.daki.api.controller;


import com.daki.api.request.diary.DiaryCreateReq;
import com.daki.api.request.diary.DiaryDeleteReq;
import com.daki.api.request.diary.DiaryReadReq;
import com.daki.api.request.diary.DiaryUpdateReq;
import com.daki.api.response.diary.DiaryCreateRes;
import com.daki.api.response.diary.DiaryDeleteRes;
import com.daki.api.response.diary.DiaryReadRes;
import com.daki.api.response.diary.DiaryUpdateRes;
import com.daki.api.service.DiaryService;
import com.daki.api.service.DiaryServiceImpl;
import com.daki.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/diary")
public class DiaryController {

    @Autowired
    DiaryServiceImpl diaryService;

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<DiaryCreateRes> createDiary(@RequestBody DiaryCreateReq diaryCreateReq
                                                        , HttpServletRequest httpServletRequest){
        DiaryCreateRes diaryCreateRes = diaryService.createDiary(diaryCreateReq);

        return userService.tokenEnter(httpServletRequest, diaryCreateRes, 200);
    }

    @GetMapping("/{diaryNo}")
    public ResponseEntity<DiaryReadRes> readDiary(@PathVariable Long diaryNo,
                                                  HttpServletRequest httpServletRequest){
        DiaryReadRes diaryReadRes = diaryService.readDiary(diaryNo);

        return userService.tokenEnter(httpServletRequest, diaryReadRes, 200);
    }

    @PutMapping
    public ResponseEntity<DiaryUpdateRes> updateDiary(@RequestBody DiaryUpdateReq diaryUpdateReq,
                                                      HttpServletRequest httpServletRequest){
        DiaryUpdateRes diaryUpdateRes = diaryService.updateDiary(diaryUpdateReq);

        return userService.tokenEnter(httpServletRequest, diaryUpdateRes, 200);
    }

    @DeleteMapping("/{diaryNo}")
    public ResponseEntity<DiaryDeleteRes> deleteDiary(@PathVariable Long diaryNo,
                                                      HttpServletRequest httpServletRequest){
        DiaryDeleteRes diaryDeleteRes = diaryService.deleteDiary(diaryNo);
        System.out.println("====================Success diary delete=======================");
        return userService.tokenEnter(httpServletRequest, diaryDeleteRes, 200);
    }




}
