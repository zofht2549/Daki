package com.daki.api.service;


import com.daki.api.request.DollCreateReq;
import com.daki.api.request.DollLikeableUpdateReq;
import com.daki.api.request.DollUpdateReq;
import com.daki.api.response.*;
import com.daki.db.entity.Doll;
import com.daki.db.entity.Skin;
import com.daki.db.entity.User;
import com.daki.db.repository.DollRepository;
import com.daki.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DollServiceImpl implements DollService{

    @Autowired
    DollRepository dollRepository;

    @Autowired
    UserRepository userRepository;

    public DollReadRes readDollInfo(Long dollNo){
        Doll doll = dollRepository.getById(dollNo);
        DollReadRes dollReadRes = new DollReadRes(doll.getDollNo(), doll.getDollLikeable(), doll.getSkin());

        return dollReadRes;
    }

    public DollUpdateRes updateDollInfo(DollUpdateReq dollUpdateReq){
        User user = userRepository.getById(dollUpdateReq.getUserNo());
        Doll findDoll = dollRepository.findByUser(user);
        Doll updateDoll = new Doll(findDoll.getDollNo(), dollUpdateReq.getDollLikeable(), findDoll.getUser(), dollUpdateReq.getSkin());

        DollUpdateRes dollUpdateRes = new DollUpdateRes(dollRepository.save(updateDoll));

        return dollUpdateRes;
    }

    public DollDeleteRes deleteDoll(Long dollNo){
        Doll doll = dollRepository.getById(dollNo);
        dollRepository.delete(doll);

        DollDeleteRes dollDeleteRes = new DollDeleteRes("Delete success.");

        return dollDeleteRes;
    }

    public DollLikeableUpdateRes updateDollLikeable(DollLikeableUpdateReq dollLikeableUpdateReq) {
        Doll findDoll = dollRepository.getById(dollLikeableUpdateReq.getDollNo());

        int changedLikeable = findDoll.getDollLikeable() + dollLikeableUpdateReq.getChangeAmount();
        if(changedLikeable<0) changedLikeable = 0;
        if(changedLikeable>100) changedLikeable = 100;

        Doll updateDoll = new Doll(findDoll.getDollNo(), changedLikeable, findDoll.getUser(), findDoll.getSkin());

        DollLikeableUpdateRes dollLikeableUpdateRes = new DollLikeableUpdateRes(dollRepository.save(updateDoll));

        return dollLikeableUpdateRes;
    }
}
