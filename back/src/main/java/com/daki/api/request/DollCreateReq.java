package com.daki.api.request;

import com.daki.db.entity.Skin;
import com.daki.db.entity.User;

import javax.persistence.*;

public class DollCreateReq {

    private int dollLikeable;

    private String userNickname;

    private Skin skin;

    public int getDollLikeable() {
        return dollLikeable;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public Skin getSkin() {
        return skin;
    }

    public DollCreateReq(int dollLikeable, String userNickname, Skin skin) {
        this.dollLikeable = dollLikeable;
        this.userNickname = userNickname;
        this.skin = skin;
    }

}
