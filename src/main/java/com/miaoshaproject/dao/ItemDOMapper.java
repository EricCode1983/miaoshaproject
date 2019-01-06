package com.miaoshaproject.dao;

import com.miaoshaproject.dataobject.ItemDO;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ItemDOMapper {

    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item
     *
     * @mbg.generated Tue Jan 01 10:25:19 WIB 2019
     */
    int insert(ItemDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item
     *
     * @mbg.generated Tue Jan 01 10:25:19 WIB 2019
     */
    int insertSelective(ItemDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item
     *
     * @mbg.generated Tue Jan 01 10:25:19 WIB 2019
     */


    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item
     *
     * @mbg.generated Tue Jan 01 10:25:19 WIB 2019
     */
    ItemDO selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item
     *
     * @mbg.generated Tue Jan 01 10:25:19 WIB 2019
     */


    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item
     *
     * @mbg.generated Tue Jan 01 10:25:19 WIB 2019
     */


    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item
     *
     * @mbg.generated Tue Jan 01 10:25:19 WIB 2019
     */
    int updateByPrimaryKeySelective(ItemDO record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table item
     *
     * @mbg.generated Tue Jan 01 10:25:19 WIB 2019
     */
    int updateByPrimaryKey(ItemDO record);

    List<ItemDO> listItem();

    int increaseSales(@Param("id") Integer id,@Param("amount") Integer amount);
}