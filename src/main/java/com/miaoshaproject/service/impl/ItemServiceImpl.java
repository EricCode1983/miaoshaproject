package com.miaoshaproject.service.impl;

import com.miaoshaproject.dao.ItemDOMapper;
import com.miaoshaproject.dao.ItemStockDOMapper;
import com.miaoshaproject.dataobject.ItemDO;
import com.miaoshaproject.dataobject.ItemStockDO;
import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.error.EmBusinessError;
import com.miaoshaproject.service.ItemService;
import com.miaoshaproject.service.model.ItemModel;
import com.miaoshaproject.validator.ValidationResult;
import com.miaoshaproject.validator.ValidatorImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ValidatorImpl validator;
    @Autowired
    private ItemDOMapper itemDOMapper;
    @Autowired
    private ItemStockDOMapper itemStockDOMapper;

    private ItemDO convertItemDOFromItemModel(ItemModel itemModel){
        if(itemModel==null){
            return null;
        }
        ItemDO itemDO=new ItemDO();
        BeanUtils.copyProperties(itemModel,itemDO);
        itemDO.setPrice(itemModel.getPrice().doubleValue());
        return itemDO;
    }

    private ItemStockDO convertItemStockDOFromItemModel(ItemModel itemModel){
        if(itemModel==null){
            return null;
        }
        ItemStockDO itemStockDO=new ItemStockDO();
        itemStockDO.setItemId(itemModel.getId());
        itemStockDO.setStock(itemModel.getStock());
        return itemStockDO;
    }

    @Override
    @Transactional
    public ItemModel createItem(ItemModel itemModel) throws BusinessException {
        //check input parameter
       ValidationResult validationResult= validator.validate(itemModel);
       if(validationResult.isHasErrors())
       {
           throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,validationResult.getErrMsg());
       }


        //itemmodel->dataobject
        ItemDO itemDO=this.convertItemDOFromItemModel(itemModel);
        //insert to database
        itemDOMapper.insertSelective(itemDO);
        itemModel.setId(itemDO.getId());

        ItemStockDO itemStockDO=this.convertItemStockDOFromItemModel(itemModel);
        itemStockDOMapper.insertSelective(itemStockDO);

        //return create item
        return this.getItemById(itemModel.getId());
    }

    @Override
    public List<ItemModel> listItem() {
      List<ItemDO> itemDOLIst=  itemDOMapper.listItem();
      List<ItemModel> itemModelList= itemDOLIst.stream().map(itemDO -> {
          ItemStockDO itemStockDO=itemStockDOMapper.selectByItemId(itemDO.getId());
          ItemModel itemModel=this.convertModelFormDataObject(itemDO,itemStockDO);
          return itemModel;
      }).collect(Collectors.toList());
      return itemModelList;
    }

    @Override
    public ItemModel getItemById(Integer id) {

        ItemDO itemDO=itemDOMapper.selectByPrimaryKey(id);
        if(itemDO==null){
            return null;
        }
        //get item stock

        ItemStockDO itemStockDO= itemStockDOMapper.selectByItemId(itemDO.getId());
        ItemModel itemModel=convertModelFormDataObject(itemDO,itemStockDO);
        return itemModel;
    }

    @Transactional
    @Override
    public boolean decreaseStock(Integer itemId, Integer amount) throws BusinessException {

        int affectedRow=itemStockDOMapper.decreaseStock(itemId,amount);
        //Update successfully
        if(affectedRow>0){
            return true;
        }
        else {
        //Update failed
            return false;
        }
    }

    @Override
    @Transactional
    public void increaseSales(Integer itemId, Integer amount) throws BusinessException {
            itemDOMapper.increaseSales(itemId,amount);

    }

    private ItemModel convertModelFormDataObject(ItemDO itemdo, ItemStockDO itemStockDO){
        ItemModel itemModel=new ItemModel();
        BeanUtils.copyProperties(itemdo,itemModel);
        itemModel.setPrice(new BigDecimal(itemdo.getPrice()));
        itemModel.setStock(itemStockDO.getStock());
        return itemModel;
    }


}
