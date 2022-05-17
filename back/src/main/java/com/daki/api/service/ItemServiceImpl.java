package com.daki.api.service;

import com.daki.api.response.ItemReadResInterface;
import com.daki.db.entity.Doll;
import com.daki.db.entity.Item;
import com.daki.db.repository.DollRepository;
import com.daki.db.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService{

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    DollRepository dollRepository;

    public List<ItemReadResInterface> readAllItem(Long dollNo){
        Doll doll = dollRepository.getById(dollNo);
        List<ItemReadResInterface> list = new ArrayList<>();
        return itemRepository.findByDoll(dollNo);
    }

}
