package com.daki.api.response;


import com.daki.db.entity.Doll;
import com.daki.db.entity.Skin;
import com.daki.db.entity.User;

import javax.persistence.*;

public class DollUpdateRes {

    private Long dollNo;

    private int dollLikeable;

    private Skin skin;

    public Long getDollNo() {
        return dollNo;
    }

    public int getDollLikeable() {
        return dollLikeable;
    }

    public Skin getSkin() {
        return skin;
    }

    public void setDollNo(Long dollNo) {
        this.dollNo = dollNo;
    }

    public void setDollLikeable(int dollLikeable) {
        this.dollLikeable = dollLikeable;
    }

    public void setSkin(Skin skin) {
        this.skin = skin;
    }

    public DollUpdateRes(Long dollNo, int dollLikeable, Skin skin) {
        this.dollNo = dollNo;
        this.dollLikeable = dollLikeable;
        this.skin = skin;
    }

    public DollUpdateRes(Doll doll){
        this.dollNo = doll.getDollNo();
        this.dollLikeable = doll.getDollLikeable();
        this.skin = doll.getSkin();
    }

}
