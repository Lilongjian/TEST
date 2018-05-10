package com.situ.mall.portal.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.situ.mall.common.response.ServerResponse;
import com.situ.mall.core.constant.Const;
import com.situ.mall.core.entity.Order;
import com.situ.mall.core.entity.OrderItem;
import com.situ.mall.core.entity.Product;
import com.situ.mall.core.entity.Shipping;
import com.situ.mall.core.entity.User;
import com.situ.mall.core.service.IOrderItemService;
import com.situ.mall.core.service.IOrderService;
import com.situ.mall.core.service.IProductService;
import com.situ.mall.core.service.IShippingService;
import com.situ.mall.portal.vo.CartItemVo;
import com.situ.mall.portal.vo.CartVo;
import com.situ.mall.portal.vo.OrderVo;

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
   public String getOrderPage(HttpSession session,HttpServletRequest request,Model model){
	   //1.从session中获得user对象
	   User user = (User) session.getAttribute("CURRENT_USER");
	   //2通过user得到收货地址
	  List<Shipping> shippings =  shippingService.selectByUserId(user.getId());
	  System.out.println(shippings);
	  model.addAttribute("shippings", shippings);
	  //3.将cookie里面的购物信息转换为CartVo对象
	  CartVo cartVo = getCartVoFromCookies(request);
	  //将CartItemVo里面的product填满信息，因为现在只有一个id
	  List<CartItemVo> cartItemVos = cartVo.getCartItemVos();
	  //for循环这种方式，remove的时候报错，要用迭代器
	 /* for (CartItemVo cartItemVo : cartItemVos) {
		  if (cartItemVo.getIsChecked()==1) {
		Product product = productService.selectById(cartItemVo.getProduct().getId());
		System.out.println(product);
		cartItemVo.setProduct(product);
		  }
	}*/
	  Iterator<CartItemVo> iterator = cartItemVos.iterator();
	  while (iterator.hasNext()) {
		CartItemVo item = (CartItemVo) iterator.next();
		//没有勾选的移除
		if (item.getIsChecked() == Const.CartChecked.UNCHECKED) {
			iterator.remove();
		}else{
			//勾选的将product更新为详细信息
			Product product = productService.selectById(item.getProduct().getId());
			item.setProduct(product);
		}
		
	}
	  model.addAttribute("cartVo", cartVo);
	   return "order";
   }
   
   
   private CartVo getCartVoFromCookies(HttpServletRequest request) {
		CartVo cartVo = null;
		ObjectMapper objectMapper = new ObjectMapper();
		//只有对象中不为null才转换
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		//将客户端中购物车cookie拿出来
		//springmvc
		Cookie[] cookies = request.getCookies(); 
		if (null != cookies && cookies.length != 0) {
			for (Cookie cookie : cookies) {
				if (CART_COOKIE.equals(cookie.getName())) {//找到了客户端cookie中购物车信息
					String value = cookie.getValue();
					try {
						//将json字符串转换为对象
						cartVo = objectMapper.readValue(value, CartVo.class);
					} catch (JsonParseException e) {
						e.printStackTrace();
					} catch (JsonMappingException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return cartVo;
	}
   
   @RequestMapping("/addOrder")
   @ResponseBody
   public ServerResponse addOrder(Integer shippingId,Integer totalprice,HttpSession session,HttpServletRequest request,Model model){
	   //1.创建订单对象
	   Order order = new Order();
	   Long curr = System.currentTimeMillis();
       SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
       Date date = new Date(curr);
       order.setOrderNo(Long.parseLong(format.format(date)));
       User user = (User) session.getAttribute("CURRENT_USER");
       order.setUserId(user.getId());
       order.setShippingId(shippingId);
       BigDecimal total = new BigDecimal(totalprice);
       order.setPayment(total);
       //2.将订单插入数据库
       int count = orderService.add(order);
       if (count > 0) {
		System.out.println("添加成功");
	}
       //3.从cookies里面得到cartvo
       CartVo cartVo = getCartVoFromCookies(request);
       List<CartItemVo> cartItemVos = cartVo.getCartItemVos();
       OrderVo orderVo = new OrderVo();
       for (CartItemVo item : cartItemVos) {
		  //购物车里面选中的加入购物车
    	   if (item.getIsChecked()==Const.CartChecked.CHECKED) {
			OrderItem orderItem = new OrderItem();
			orderItem.setOrderNo(order.getOrderNo());
			orderItem.setUserId(user.getId());
			Product product = productService.selectById(item.getProduct().getId());
	        orderItem.setProductId(product.getId());
	        orderItem.setCurrentUnitPrice(product.getPrice());
	        orderItem.setProductName(product.getName());
	        orderItem.setQuantity(item.getAmount());
	        orderItem.setTotalPrice(order.getPayment());
	        orderItem.setProductImage(product.getMainImage());
	        orderItemService.addOrderItem(orderItem);
	        //orderVo.setOrderItems(orderItem);
		}
	}
       //4.遍历cartvo将所有ischecked是1的删除，然后写道cookies
       Iterator<CartItemVo> iterator = cartItemVos.iterator();
       while (iterator.hasNext()) {
		CartItemVo item = (CartItemVo) iterator.next();
		if (item.getIsChecked()==Const.CartChecked.CHECKED) {
			iterator.remove();
		}
		
	}
	   return ServerResponse.createSuccess("添加数据库成功");
   }
   
   
   @RequestMapping("/getApyPage")
   public String getApyPage (Model model,HttpSession session){
	   User user = (User) session.getAttribute("CURRENT_USER");
	  List<Order> orders = orderService.selectAll(user.getId());
	  model.addAttribute("orders", orders);
	   return "apy";
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
