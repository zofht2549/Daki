package com.daki.db.entity;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "item")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_no")
    private Long itemNo;

    @Enumerated(EnumType.STRING)
    @Column(name = "item_type")
    private ItemType itemType;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "item_image")
    private String itemImage;

    public Item(ItemType itemType, String itemName, String itemImage) {
        this.itemType = itemType;
        this.itemName = itemName;
        this.itemImage = itemImage;
    }
}
