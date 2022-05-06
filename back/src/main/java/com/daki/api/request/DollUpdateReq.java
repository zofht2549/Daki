package com.daki.api.request;

import com.daki.db.entity.Skin;

public class DollUpdateReq {

    private long userNo;
    private int dollLikeable;
    private Skin skin;

    public long getUserNo() {return userNo;}

    public int getDollLikeable() {
        return dollLikeable;
    }

    public Skin getSkin() {
        return skin;
    }

    public void setUserNo(long userNo) {this.userNo = userNo;}

    public void setDollLikeable(int dollLikeable) {
        this.dollLikeable = dollLikeable;
    }

    public void setSkin(Skin skin) {
        this.skin = skin;
    }

    public DollUpdateReq(){}

    public DollUpdateReq(long userNo, int dollLikeable, Skin skin){
        this.userNo = userNo;
        this.dollLikeable = dollLikeable;
        this.skin = skin;
    }

}
