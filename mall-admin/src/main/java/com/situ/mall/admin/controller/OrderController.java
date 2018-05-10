package com.situ.mall.admin.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.situ.mall.common.response.ServerResponse;
import com.situ.mall.core.entity.Order;
import com.situ.mall.core.entity.OrderItem;
import com.situ.mall.core.entity.Product;
import com.situ.mall.core.entity.User;
import com.situ.mall.core.service.IOrderItemService;
import com.situ.mall.core.service.IOrderService;
import com.situ.mall.core.service.IProductService;
import com.situ.mall.core.service.IShippingService;

@Controller
@RequestMapping("/order")
public class OrderController {
	private String CART_COOKIE = "cart_cookie";
	@Autowired
	private IProductService productService;
	@Autowired
	private IShippingService shippingService;
	@Autowired
	private  IOrderService orderService;
	@Autowired
	private IOrderItemService orderItemService;
   
   @RequestMapping("/getOrderPage")
   public String getApyPage (){
	   return "order";
   }
   @RequestMapping("/getOrderList")
   @ResponseBody
   public ServerResponse<List<Order>> getOrderList (Integer page, Integer limit,Order order){
	  return orderService.selectAlls(page,limit,order);
   }
  /* value="/login",method=RequestMethod.POST*/
  /* @RequestMapping(value="/getOrderItemData",method=RequestMethod.POST)
   @ResponseBody
   public ServerResponse getOrderItemData (Integer orderNo,Model model){
	  System.out.println(orderNo);
	  List<OrderItem> orderItems = orderItemService.selectByOrderNo(orderNo);
	  System.out.println(orderItems);
	  model.addAttribute("orderItems", orderItems);
	  return ServerResponse.createSuccess("查询成功");
   }*/
   @RequestMapping("/getOrderItemPage")
   public String getOrderItemPage (Long orderNo,Model model){
	   System.out.println(orderNo);
	   List<OrderItem> orderItems = orderItemService.selectByOrderNo(orderNo);
	   System.out.println(orderItems); 
	    model.addAttribute("orderItems", orderItems);
	   return "orderItems";
   }
}
