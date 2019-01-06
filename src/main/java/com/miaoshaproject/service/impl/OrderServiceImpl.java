package com.miaoshaproject.service.impl;

import com.miaoshaproject.dao.OrderDOMapper;
import com.miaoshaproject.dao.SequenceDOMapper;
import com.miaoshaproject.dataobject.OrderDO;
import com.miaoshaproject.dataobject.SequenceDO;
import com.miaoshaproject.error.BusinessException;
import com.miaoshaproject.error.EmBusinessError;
import com.miaoshaproject.service.ItemService;
import com.miaoshaproject.service.OrderService;
import com.miaoshaproject.service.UserService;
import com.miaoshaproject.service.model.ItemModel;
import com.miaoshaproject.service.model.OrderModel;
import com.miaoshaproject.service.model.UserModel;
import org.apache.catalina.User;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.lang.model.element.NestingKind;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ItemService itemService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderDOMapper orderDOMapper;

    @Autowired
    private SequenceDOMapper sequenceDOMapper;

    @Override
    @Transactional
    public OrderModel createOrder(Integer userId, Integer itemId, Integer amount) throws BusinessException {
        //1 verify        order, order item exist?, order legal?, order amount correct?

        ItemModel itemModel=itemService.getItemById(itemId);
        if(itemModel==null){
            throw  new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"item not existed");
        }

        UserModel userModel=userService.getUserById(userId);
        if(userModel==null){
            throw  new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"user not existed");
        }

        if(amount<=0||amount>99){
            throw  new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"amount info not correct.");

        }

        //2 order then deduct stocks
        boolean result= itemService.decreaseStock(itemId, amount);
        if(!result){
            throw  new BusinessException(EmBusinessError.STOCK_NOT_ENOUGH,"stock not enough ");
        }

        //3. insert order to database
        OrderModel orderModel=new OrderModel();
        orderModel.setUserId(userId);
        orderModel.setItemId(itemId);
        orderModel.setAmount(amount);
        orderModel.setItemPrice(itemModel.getPrice());
        orderModel.setOrderPrice(itemModel.getPrice().multiply(new BigDecimal(amount)));

        //generate order id
        orderModel.setId(generateOrderNo());
        OrderDO orderDO=convertFromOrderModel(orderModel);
        try{
            orderDOMapper.insertSelective(orderDO);
        }
        catch (Exception ex){
            System.out.println("test");
        }
        itemService.increaseSales(itemId,amount);
        //4. return order
        return orderModel;
    }

//    public static void main(String[] args){
//        LocalDateTime now=LocalDateTime.now();
//        System.out.println( now.format(DateTimeFormatter.ISO_DATE).replace("-",""));
//
//    }

    //Even the transaction failed, the order no still will be use the new one
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    private String generateOrderNo(){
        //order id 16 integer
        // first 8 number is year-month-date
        StringBuilder stringBuilder=new StringBuilder();
        LocalDateTime now=LocalDateTime.now();
         String nowDate=now.format(DateTimeFormatter.ISO_DATE).replace("-","");
         stringBuilder.append(nowDate);
        //middle 6 is auto increase number
        int sequence=0;
        SequenceDO sequenceDO= sequenceDOMapper.getSequenceByName("order_info");
        sequence=sequenceDO.getCurrentValue();
        sequenceDO.setCurrentValue(sequenceDO.getCurrentValue()+sequenceDO.getStep());
        sequenceDOMapper.updateByPrimaryKey(sequenceDO);

        String sequenceStr=String.valueOf(sequence);
        for(int i=0;i<6-sequenceStr.length();i++){
            stringBuilder.append(0);
        }

        stringBuilder.append(sequenceStr);
        //last 2 number is used to later separate to difference database and table
        stringBuilder.append("00");
        System.out.println(stringBuilder.toString());
        return stringBuilder.toString();
    }


    private OrderDO convertFromOrderModel(OrderModel orderModel){
        if (orderModel == null) {
            return null;
        }
        OrderDO orderDO=new OrderDO();
        BeanUtils.copyProperties(orderModel,orderDO);
        orderDO.setOrderPrice(orderModel.getOrderPrice().doubleValue());
        orderDO.setItemPrice(orderModel.getOrderPrice().doubleValue());
        return orderDO;
    }
}
