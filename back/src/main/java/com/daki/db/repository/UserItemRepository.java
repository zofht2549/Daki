package com.daki.db.repository;

import com.daki.api.response.UserItemReadRes;
import com.daki.api.response.UserItemReadResInterface;
import com.daki.db.entity.Doll;
import com.daki.db.entity.Item;
import com.daki.db.entity.UserItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserItemRepository extends JpaRepository<UserItem, Long> {

//    @Query(value="select A.item_no as itemNo, A.wear_flag AS wearFlag, B.item_image AS itemImage, B.item_name as itemName, B.item_type as itemType from user_item as A\n" +
//            "left join item as B\n" +
//            "on A.item_no = B.item_no\n" +
//            "where doll_no=:doll", nativeQuery = true)
    List<UserItemReadResInterface> findByDoll(@Param("doll") Doll doll);

    UserItem findByDollAndItem(Doll doll, Item item);
    List<UserItem> findByDollAndWearFlag(Doll doll, boolean wearFlag);

//    @Query(value="select * from user_item where doll_no=:doll_no", nativeQuery = true)
//    List<UserItem> findByDoll2(@Param("doll_no") Long dollNo);
}
