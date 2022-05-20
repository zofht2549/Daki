package com.daki.api.service;

import com.daki.api.request.UserItemCreateReq;
import com.daki.api.request.UserItemUpdateWearStateReq;
import com.daki.api.response.UserItemCreateRes;
import com.daki.api.response.UserItemReadRes;
import com.daki.api.response.UserItemReadResInterface;
import com.daki.api.response.UserItemUpdateWearStateRes;
import com.daki.db.entity.Doll;
import com.daki.db.entity.Item;
import com.daki.db.entity.User;
import com.daki.db.entity.UserItem;
import com.daki.db.repository.DollRepository;
import com.daki.db.repository.ItemRepository;
import com.daki.db.repository.UserItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserItemServiceImpl implements UserItemService{

    @Autowired
    DollRepository dollRepository;

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    UserItemRepository userItemRepository;


    public List<UserItemReadResInterface> readUserItem(Long dollNo){
        Doll doll = dollRepository.getById(dollNo);
        List<UserItemReadResInterface> userItemList = userItemRepository.findByDoll(doll);

        return userItemList;
    }

    public UserItemCreateRes createUserItem(UserItemCreateReq userItemCreateReq){
        Doll doll = dollRepository.getById(userItemCreateReq.getDollNo());
        Item item = itemRepository.getById(userItemCreateReq.getItemNo());
        UserItem userItem = new UserItem(0, doll, item);
        return new UserItemCreateRes(userItemRepository.save(userItem));
    }


    public UserItemUpdateWearStateRes updateWearState(UserItemUpdateWearStateReq userItemUpdateWearStateReq){
        Doll doll = dollRepository.getById(userItemUpdateWearStateReq.getDollNo());
        Item item = itemRepository.getById(userItemUpdateWearStateReq.getItemNo());
        UserItem findUserItem = userItemRepository.findByDollAndItem(doll, item);

        System.out.println("UserItemNo : "+findUserItem.getUserItemNo());

        int wearFlag = findUserItem.getWearFlag();
        if(wearFlag ==0) wearFlag =1;
        else wearFlag = 0;

        UserItem updateItem = new UserItem(findUserItem.getUserItemNo(), wearFlag, doll, item);
        UserItemUpdateWearStateRes userItemUpdateWearStateRes = new UserItemUpdateWearStateRes(userItemRepository.save(updateItem));

        return userItemUpdateWearStateRes;
    }

}
