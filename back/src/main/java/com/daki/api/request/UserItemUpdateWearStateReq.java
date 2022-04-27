package com.daki.api.request;

import com.daki.db.entity.Doll;

public class UserItemUpdateWearStateReq {

    Long dollNo;
    Long itemNo;

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

    public UserItemUpdateWearStateReq() {}

    public UserItemUpdateWearStateReq(Long dollNo, Long itemNo) {
        this.dollNo = dollNo;
        this.itemNo = itemNo;
    }
}
