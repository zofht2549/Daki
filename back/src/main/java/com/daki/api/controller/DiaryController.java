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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/diary")
public class DiaryController {

    @Autowired
    DiaryServiceImpl diaryService;

    @PostMapping
    public ResponseEntity<DiaryCreateRes> createDiary(@RequestBody DiaryCreateReq diaryCreateReq){
        DiaryCreateRes diaryCreateRes = diaryService.createDiary(diaryCreateReq);

        return ResponseEntity.status(200).body(diaryCreateRes);
    }

    @GetMapping
    public ResponseEntity<DiaryReadRes> readDiary(@PathVariable DiaryReadReq diaryReadReq){
        DiaryReadRes diaryReadRes = diaryService.readDiary(diaryReadReq);

        return ResponseEntity.status(200).body(diaryReadRes);
    }

    @PutMapping
    public ResponseEntity<DiaryUpdateRes> updateDiary(@RequestBody DiaryUpdateReq diaryUpdateReq){
        DiaryUpdateRes diaryUpdateRes = diaryService.updateDiary(diaryUpdateReq);

        return ResponseEntity.status(200).body(diaryUpdateRes);
    }

    @DeleteMapping
    public ResponseEntity<DiaryDeleteRes> deleteDiary(@PathVariable DiaryDeleteReq diaryDeleteReq){
        DiaryDeleteRes diaryDeleteRes = diaryService.deleteDiary(diaryDeleteReq);

        return ResponseEntity.status(200).body(diaryDeleteRes);
    }




}
