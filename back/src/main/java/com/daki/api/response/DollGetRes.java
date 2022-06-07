package com.daki.api.response;


import com.daki.db.entity.Skin;

public class DollGetRes {

    private int dollLikeable;
    private Skin skin;

    public Skin getSkin() {
        return skin;
    }

    public int getDollLikeable() {
        return dollLikeable;
    }

    public void setSkin(Skin skin) {
        this.skin = skin;
    }

    public void setDollLikeable(int dollLikeable) {
        this.dollLikeable = dollLikeable;
    }

    public DollGetRes(int dollLikeable, Skin skin){
        this.dollLikeable = dollLikeable;
        this.skin = skin;
    }


}
