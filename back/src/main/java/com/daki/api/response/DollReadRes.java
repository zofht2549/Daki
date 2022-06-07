package com.daki.api.response;


import com.daki.db.entity.Skin;

public class DollReadRes {

    private Long dollNo;
    private int dollLikeable;
    private Skin skin;

    public Long getDollNo() { return dollNo;}

    public Skin getSkin() {
        return skin;
    }

    public int getDollLikeable() {
        return dollLikeable;
    }

    public void setDollNo(Long dollNo) { this.dollNo = dollNo;}

    public void setSkin(Skin skin) {
        this.skin = skin;
    }

    public void setDollLikeable(int dollLikeable) {
        this.dollLikeable = dollLikeable;
    }

    public DollReadRes(Long dollNo, int dollLikeable, Skin skin){
        this.dollNo = dollNo;
        this.dollLikeable = dollLikeable;
        this.skin = skin;
    }


}
