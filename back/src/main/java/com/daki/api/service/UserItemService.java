package com.daki.api.service;

import com.daki.api.request.UserItemUpdateWearStateReq;
import com.daki.api.response.UserItemReadRes;
import com.daki.api.response.UserItemReadResInterface;
import com.daki.api.response.UserItemUpdateWearStateRes;

import java.util.List;

public interface UserItemService {

    List<UserItemReadResInterface> readUserItem(Long dollNo);

    UserItemUpdateWearStateRes updateWearState(UserItemUpdateWearStateReq userItemUpdateWearStateReq);

}
