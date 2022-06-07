package com.daki.db.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "user_item")
public class UserItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_item_no")
    private Long userItemNo;

    @Column(name = "wear_flag")
    private boolean wearFlag;

    @ManyToOne
    @JoinColumn(name = "doll_no")
    private Doll doll;

    @ManyToOne
    @JoinColumn(name = "item_no")
    private Item item;


    public UserItem(boolean wearFlag, Doll doll, Item item) {
        this.wearFlag = wearFlag;
        this.doll = doll;
        this.item = item;
    }
}
