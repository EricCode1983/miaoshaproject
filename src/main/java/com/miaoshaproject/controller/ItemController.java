package com.miaoshaproject.controller;


import com.alibaba.druid.sql.ast.expr.SQLCaseExpr;
import com.miaoshaproject.controller.viewobject.ItemVO;
import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.response.CommonReturnType;
import com.miaoshaproject.service.ItemService;
import com.miaoshaproject.service.model.ItemModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Controller("item")
@RequestMapping("/item")
@CrossOrigin(allowCredentials = "true",allowedHeaders = "*")
public class ItemController extends BaseController {
    @Autowired
    private ItemService itemService;
    //create item
    @RequestMapping("/create")
    @ResponseBody
    public CommonReturnType createItem(@RequestParam(name = "title")String title,
                                      @RequestParam(name = "desc")String description,
                                      @RequestParam(name = "price") BigDecimal price,
                                      @RequestParam(name = "stock")Integer stock,
                                      @RequestParam(name = "imgUrl")String imgUrl
                                      ) throws BusinessException {
        //box service request
        ItemModel itemModel=new ItemModel();
        itemModel.setTitle(title);
        itemModel.setDescription(description);
        itemModel.setPrice(price);
        itemModel.setStock(stock);
        itemModel.setImgUrl(imgUrl);
        ItemModel itemModelForReturn=itemService.createItem(itemModel);
        ItemVO itemVO=convertVOFromModel(itemModelForReturn);
        return CommonReturnType.create(itemVO);
    }

    @RequestMapping(value = "/get",method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType getItem(@RequestParam(name = "id") Integer id){
        ItemModel itemModel=itemService.getItemById(id);

        ItemVO itemVO=convertVOFromModel(itemModel);
        return CommonReturnType.create(itemVO);
    }



    @RequestMapping(value = "/list",method = {RequestMethod.GET})
    @ResponseBody
    public CommonReturnType listItem(){

        List<ItemModel> itemModelList= itemService.listItem();

        //User stream api convert item model to itemvo
        List<ItemVO> itemVOList= itemModelList.stream().map(itemModel -> {
            ItemVO itemVO=this.convertVOFromModel(itemModel);
            return itemVO;
        }).collect(Collectors.toList()) ;
        return CommonReturnType.create(itemVOList);
    }




    private ItemVO convertVOFromModel(ItemModel itemModel){
        if (itemModel == null) {
            return null;
        }
        ItemVO itemVO=new ItemVO();
        BeanUtils.copyProperties(itemModel,itemVO);
        return itemVO;
    }
}
