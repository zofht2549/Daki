package com.daki.api.controller;

import com.daki.api.request.UserItemUpdateWearStateReq;
import com.daki.api.response.UserItemReadResInterface;
import com.daki.api.response.UserItemUpdateWearStateRes;
import com.daki.api.service.UserItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/useritem")
public class UserItemController {

    @Autowired
    UserItemServiceImpl useritemService;

    @GetMapping
    ResponseEntity<List<UserItemReadResInterface>> readUserItem(@RequestParam Long dollNo){
        List<UserItemReadResInterface> userItemReadRes = useritemService.readUserItem(dollNo);

        return ResponseEntity.status(200).body(userItemReadRes);
    }

    @PutMapping("/wear")
    ResponseEntity<UserItemUpdateWearStateRes> updateWearState(@RequestBody UserItemUpdateWearStateReq userItemUpdateWearStateReq){
        UserItemUpdateWearStateRes userItemUpdateWearStateRes = useritemService.updateWearState(userItemUpdateWearStateReq);

        return ResponseEntity.status(200).body(userItemUpdateWearStateRes);
    }

}
