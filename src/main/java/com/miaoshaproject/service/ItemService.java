package com.miaoshaproject.service;

import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.service.model.ItemModel;

import java.util.List;

public interface ItemService {


    //create item
    ItemModel createItem(ItemModel itemModel) throws BusinessException;

    List<ItemModel> listItem();

    ItemModel getItemById(Integer id);

    //Stock deduct
    boolean decreaseStock(Integer itemId,Integer amount )  throws BusinessException;

    //item sales increase
    void increaseSales(Integer itemId,Integer amount) throws BusinessException;
}
