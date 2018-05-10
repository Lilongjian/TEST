package com.situ.mall.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.situ.mall.core.entity.Shipping;
import com.situ.mall.core.mapper.ShippingMapper;
import com.situ.mall.core.service.IShippingService;
@Service
public class ShippingServiceImpl implements IShippingService{
	  @Autowired
	    private ShippingMapper shippingMapper;

	@Override
	public List<Shipping> selectByUserId(Integer id) {
		return (List<Shipping>) shippingMapper.selectByUserPrimaryKey(id);
	}


}
