package com.situ.mall.core.service;

import java.util.List;

import com.situ.mall.common.response.ServerResponse;
import com.situ.mall.core.entity.Order;

public interface IOrderService {

	int add(Order order);

	List<Order> selectAll(Integer userId);

	ServerResponse<List<Order>> selectAlls(Integer page, Integer limit, Order order);


}
