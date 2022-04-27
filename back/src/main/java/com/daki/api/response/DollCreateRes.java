package com.daki.api.response;

import com.daki.db.entity.Doll;
import com.daki.db.entity.Skin;
import com.daki.db.entity.User;

import javax.persistence.*;

public class DollCreateRes {

    private Long dollNo;

    private int dollLikeable;

    private Skin skin;

    public DollCreateRes(Long dollNo, int dollLikeable, Skin skin) {
        this.dollNo = dollNo;
        this.dollLikeable = dollLikeable;
        this.skin = skin;
    }

}
