package com.mmall.dao;

import com.mmall.pojo.order_item;

public interface order_itemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(order_item record);

    int insertSelective(order_item record);

    order_item selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(order_item record);

    int updateByPrimaryKey(order_item record);
}