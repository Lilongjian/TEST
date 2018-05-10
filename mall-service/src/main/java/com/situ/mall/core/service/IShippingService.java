package com.situ.mall.core.service;

import java.util.List;

import com.situ.mall.core.entity.Shipping;

public interface IShippingService {

	List<Shipping> selectByUserId(Integer id);

}
