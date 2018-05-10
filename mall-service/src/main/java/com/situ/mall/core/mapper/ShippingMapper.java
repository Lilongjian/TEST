package com.situ.mall.core.mapper;

import java.util.List;

import com.situ.mall.core.entity.Shipping;

public interface ShippingMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Shipping record);

    int insertSelective(Shipping record);

    Shipping selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Shipping record);

    int updateByPrimaryKey(Shipping record);

	List<Shipping> selectByUserPrimaryKey(Integer id);
}