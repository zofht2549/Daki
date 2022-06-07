package com.daki.db.entity;

import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "diary")
public class Diary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "diary_no")
    private Long diaryNo;

    @Column(name = "diary_title")
    private String diaryTitle;

    @Column(name = "diary_content")
    private String diaryContent;

    @Column(name = "diary_date")
    private LocalDateTime diaryDate;

    @ManyToOne
    @JoinColumn(name = "user_no")
    private User user;


    public Diary(String diaryTitle, String diaryContent, LocalDateTime diaryDate, User user) {
        this.diaryTitle = diaryTitle;
        this.diaryContent = diaryContent;
        this.diaryDate = diaryDate;
        this.user = user;
    }
}
