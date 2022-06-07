package com.daki.api.service;

import com.daki.api.request.UserItemUpdateWearStateReq;
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
//        List<UserItemReadRes> list = new ArrayList<>();
//        return list;
    }

    public UserItemUpdateWearStateRes updateWearState(UserItemUpdateWearStateReq userItemUpdateWearStateReq){
        Doll doll = dollRepository.getById(userItemUpdateWearStateReq.getDollNo());
        Item item = itemRepository.getById(userItemUpdateWearStateReq.getItemNo());
        UserItem findUserItem = userItemRepository.findByDollAndItem(doll, item);

        UserItem updateItem = new UserItem(findUserItem.getUserItemNo(), !findUserItem.isWearFlag(), doll, item);
        UserItemUpdateWearStateRes userItemUpdateWearStateRes = new UserItemUpdateWearStateRes(userItemRepository.save(updateItem));

        return userItemUpdateWearStateRes;
    }

}
