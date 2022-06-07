package com.daki.db.entity;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_no")
    private Long userNo;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_nickname")
    private String userNickname;

    @Column(name = "user_password")
    private String userPassword;

    @Column(name = "user_birth")
    private String userBirth;

    @Column(name = "user_gender")
    private boolean userGender;

    @Column(name = "user_point")
    private int userPoint;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    @Enumerated(EnumType.STRING)
    private Oauth oauth;

    public User(){}

    public void modify(String password, String nickName){
        this.userNickname = nickName;
        this.userPassword = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "userNo=" + userNo +
                ", userEmail='" + userEmail + '\'' +
                ", userName='" + userName + '\'' +
                ", userNickname='" + userNickname + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userBirth='" + userBirth + '\'' +
                ", userGender=" + userGender +
                ", userPoint=" + userPoint +
                ", authority=" + authority +
                ", oauth=" + oauth +
                '}';
    }

    @Builder
    public User(String userEmail, String userName, String userNickname, String userPassword, String userBirth, boolean userGender, int userPoint, Authority authority, Oauth oauth) {
        this.userEmail = userEmail;
        this.userName = userName;
        this.userNickname = userNickname;
        this.userPassword = userPassword;
        this.userBirth = userBirth;
        this.userGender = userGender;
        this.userPoint = userPoint;
        this.authority = authority;
        this.oauth = oauth;
    }

    public User(Long userNo, String userEmail, String userName, String userNickname, String userPassword, String userBirth, boolean userGender, int userPoint, Oauth oauth) {
        this.userNo = userNo;
        this.userEmail = userEmail;
        this.userName = userName;
        this.userNickname = userNickname;
        this.userPassword = userPassword;
        this.userBirth = userBirth;
        this.userGender = userGender;
        this.userPoint = userPoint;
        this.oauth = oauth;
    }
}
