package com.daki.api.response;

import com.daki.db.entity.UserItem;

public class UserItemUpdateWearStateRes {

    Long userItemNo;

    boolean wearFlag;

    Long dollNo;

    Long itemNo;

    public Long getUserItemNo() {
        return userItemNo;
    }

    public void setUserItemNo(Long userItemNo) {
        this.userItemNo = userItemNo;
    }

    public boolean isWearFlag() {
        return wearFlag;
    }

    public void setWearFlag(boolean wearFlag) {
        this.wearFlag = wearFlag;
    }

    public Long getDollNo() {
        return dollNo;
    }

    public void setDollNo(Long dollNo) {
        this.dollNo = dollNo;
    }

    public Long getItemNo() {
        return itemNo;
    }

    public void setItemNo(Long itemNo) {
        this.itemNo = itemNo;
    }

    public UserItemUpdateWearStateRes() {
    }

    public UserItemUpdateWearStateRes(UserItem userItem) {
        this.userItemNo = userItem.getUserItemNo();
        this.wearFlag = userItem.isWearFlag();
        this.dollNo = userItem.getDoll().getDollNo();
        this.itemNo = userItem.getItem().getItemNo();
    }
}
