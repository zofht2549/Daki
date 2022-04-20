package com.daki.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

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

    public User(String userEmail, String userNickname, String userPassword, String userBirth, boolean userGender, int userPoint) {
        this.userEmail = userEmail;
        this.userNickname = userNickname;
        this.userPassword = userPassword;
        this.userBirth = userBirth;
        this.userGender = userGender;
        this.userPoint = userPoint;
    }
}
