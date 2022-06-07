package com.daki.api.response;

import com.daki.db.entity.ItemType;
import org.springframework.beans.factory.annotation.Value;

public interface UserItemReadResInterface {
    public Long getItemItemNo();

    public boolean isWearFlag();

    public ItemType getItemItemType();

    public String getItemItemName();

    //@Value("#{target.item.itemImage}")
    public String getItemItemImage();

    //public String getDollDollNo();

}
