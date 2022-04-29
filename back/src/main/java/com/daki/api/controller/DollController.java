package com.daki.api.controller;

import com.daki.api.request.DollCreateReq;
import com.daki.api.request.DollLikeableUpdateReq;
import com.daki.api.request.DollUpdateReq;
import com.daki.api.response.*;
import com.daki.api.service.DollService;
import com.daki.api.service.DollServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/doll")
public class DollController {

    @Autowired
    DollServiceImpl dollService;

    @GetMapping
    public ResponseEntity<DollReadRes> getDollInfo(@RequestParam(value = "dollNo") Long dollNo){
        DollReadRes dollReadRes = dollService.readDollInfo(dollNo);
        return ResponseEntity.status(200).body(dollReadRes);
    }

    @PutMapping
    public ResponseEntity<DollUpdateRes> updateDollInfo(@RequestBody DollUpdateReq dollUpdateReq){
        DollUpdateRes dollUpdateRes = dollService.updateDollInfo(dollUpdateReq);
        return ResponseEntity.status(200).body(dollUpdateRes);
    }

    @DeleteMapping
    public ResponseEntity<DollDeleteRes> deleteDoll(@RequestParam Long dollNo){
        DollDeleteRes dollDeleteRes = dollService.deleteDoll(dollNo);
        return ResponseEntity.status(200).body(dollDeleteRes);
    }

    @PutMapping("/likeable")
    public ResponseEntity<DollLikeableUpdateRes> updateDollLikeable(@RequestBody DollLikeableUpdateReq dollLikeableUpdateReq){
        DollLikeableUpdateRes dollLikeableUpdateRes = dollService.updateDollLikeable(dollLikeableUpdateReq);

        return ResponseEntity.status(200).body(dollLikeableUpdateRes);
    }



}
