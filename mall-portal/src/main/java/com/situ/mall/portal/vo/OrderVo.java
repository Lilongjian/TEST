package com.situ.mall.portal.vo;

import java.util.ArrayList;
import java.util.List;

import com.situ.mall.core.entity.OrderItem;

public class OrderVo {
	private List<OrderItem> orderItems = new ArrayList<>();

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

   	

}
