package com.situ.mall.portal.controller;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.situ.mall.common.response.ServerResponse;
import com.situ.mall.core.constant.Const;
import com.situ.mall.core.entity.Product;
import com.situ.mall.core.service.IProductService;
import com.situ.mall.portal.vo.CartItemVo;
import com.situ.mall.portal.vo.CartVo;

@Controller
@RequestMapping("/cart")
public class CartController {
	private String CART_COOKIE = "cart_cookie";
	@Autowired
	private IProductService productService;
	
	@RequestMapping("/getCartPage")
	public String getCartPage(HttpServletRequest request, Model model) {
		
		 CartVo cartVo = getCartVoFromCookies(request);
		
		if (cartVo != null) {
		//2、将cookie里面所有的商品查询出来，转换成Cart这个对象
		List<CartItemVo> cartItemVos = cartVo.getCartItemVos();
		for (CartItemVo item : cartItemVos) {
			Product product = productService.selectById(item.getProduct().getId());
			item.setProduct(product);
		}
		//3、将Cart对象放到域对象中
		model.addAttribute("cartVo", cartVo);
		}
		return "cart";
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
	/*@RequestMapping("/addCart")
	@ResponseBody
	public ServerResponse addCart(Integer productId, Integer amount, HttpServletRequest request,
						HttpServletResponse response, Model model) {
		public String addCart(Integer productId, Integer amount, HttpServletRequest request,
				HttpServletResponse response, Model model) {
		CartVo cartVo = getCartVoFromCookies(request);
		//原来没有购物车这个cookies
		if (cartVo == null) {
			cartVo = new CartVo();
		}
			
			boolean result = addOrUpdateCartVo(productId, amount, cartVo);
			if (result == false) {
				return ServerResponse.createError("添加购物车失败");
			}
			setCartVoToCookie(response, cartVo);
			return ServerResponse.createSuccess("添加购物车成功");
		return "redirect:/cart/getCartPage.shtml";
		return ServerResponse.createSuccess("添加购物车成功");
		//return ServerResponse.createError("添加购物车失败");
		
	}*/
	@RequestMapping("/addOrUpdateCart")
	@ResponseBody
	public ServerResponse addOrUpdateCart(Integer productId, Integer amount, Boolean isChecked, HttpServletRequest request,
						HttpServletResponse response, Model model) {
		/*public String addCart(Integer productId, Integer amount, HttpServletRequest request,
				HttpServletResponse response, Model model) {*/
		CartVo cartVo = getCartVoFromCookies(request);
		//原来没有购物车这个cookies
		if (cartVo == null) {
			cartVo = new CartVo();
		}
			
			boolean result = addOrUpdateCartVo(productId, amount,isChecked,cartVo);
			if (result == false) {
				return ServerResponse.createError("添加购物车失败");
			}
			setCartVoToCookie(response, cartVo);
			return ServerResponse.createSuccess("添加购物车成功");
	}
	
	/*@RequestMapping("/updateCartStatus")
	@ResponseBody
	public ServerResponse updateCartStatus(Integer productId, Integer amount, Boolean isChecked, HttpServletRequest request,
						HttpServletResponse response, Model model) {
		public String addCart(Integer productId, Integer amount, HttpServletRequest request,
				HttpServletResponse response, Model model) {
		CartVo cartVo = getCartVoFromCookies(request);
		//原来没有购物车这个cookies
		if (cartVo == null) {
			cartVo = new CartVo();
		}
			
			boolean result = addOrUpdateCartVo(productId, amount,isChecked,cartVo);
			if (result == false) {
				return ServerResponse.createError("添加购物车失败");
			}
			setCartVoToCookie(response, cartVo);
			return ServerResponse.createSuccess("添加购物车成功");
	}*/
	@RequestMapping("/delCartItemById")
	@ResponseBody
	public ServerResponse delCartItemById(Integer productId,HttpServletRequest request,
						HttpServletResponse response) {
		CartVo cartVo = getCartVoFromCookies(request);
		//原来没有购物车这个cookies
		if (cartVo == null) {
			/*cartVo = new CartVo();
		}
			
			boolean result = addOrUpdateCartVo(productId, amount, cartVo);
			if (result == false) {*/
				return ServerResponse.createError("获取购物车失败");
			}
		   //遍历删除指定的id的购物项
		   List<CartItemVo> cartItemVos = cartVo.getCartItemVos();
		   Iterator<CartItemVo> iterator = cartItemVos.iterator();
		   while (iterator.hasNext()) {
			CartItemVo cartItemVo = (CartItemVo) iterator.next();
			if (productId.intValue() == cartItemVo.getProduct().getId().intValue()) {
				iterator.remove();
			}
			
		}
			setCartVoToCookie(response, cartVo);
			return ServerResponse.createSuccess("更新购物车成功");
		
	}
	private boolean addOrUpdateCartVo(Integer productId, Integer amount,Boolean isChecked,CartVo cartVo	) {
		if (productId != null) {
		boolean isExist = false;
		Product productTemp = productService.selectById(productId);
		List<CartItemVo> cartItemVos = cartVo.getCartItemVos();
		// 1、将要加入购物车的商品productId和amount插入cookie
		// 1.2 这个商品cookie里面没有，创建然后插入
		for (CartItemVo item : cartItemVos) {
			// 1.1 这个商品cookie里面已经有了，根据productId找到这件商品，更新数量即可
			if (item.getProduct().getId().intValue() == productId.intValue()) {
				isExist = true;
				/*//这个商品新的数量=原来购物车中这个商品数量+新添加这个商品的数量
				int newAmount = item.getAmount() + amount;
				//判断商品数量有没有超过库存
				if (amount <= cartItemVo.getProduct().getStock()) {
				if (newAmount > productTemp.getStock()) {
					//没有超过库存将这个产品数量跟新到购物车里面
					return ServerResponse.createError("加入购物车失败，超出库存");
					return false;
					return true;
				} 
					//如果数量超过库存数量则将最大库存
					//item.setAmount(cartItemVo.getProduct().getStock());
					return false;
					item.setAmount(newAmount);
					break;//更新完这个商品之后就不需要遍历了
					return true;*/
				if (amount != null) {
					int newAmount = item.getAmount() + amount;
					if (newAmount > productTemp.getStock()) {
						//没有超过库存将这个产品数量跟新到购物车里面
						return false;
					}
					item.setAmount(newAmount);
				}
				if (isChecked != null) {
					if (isChecked) {
						item.setIsChecked(Const.CartChecked.CHECKED);
					} else {
						item.setIsChecked(Const.CartChecked.UNCHECKED);
					}
				}
 				return true;//更新完这个商品数量后，后面的就不需要遍历
			}
			/*if (productTemp == null) {
				if (isChecked != null) {
					if (isChecked) {
						item.setIsChecked(Const.CartChecked.CHECKED);
					} else {
						item.setIsChecked(Const.CartChecked.UNCHECKED);
					}
				}
				return true;
			}*/
		}
		//在原来的购物车中就没有这件商品，直接添加
		if (isExist == false) {
			CartItemVo cartItemVo = new CartItemVo();
			Product product = new Product();
			product.setId(productId);
			//product.setStock(productTemp.getStock());
			cartItemVo.setProduct(product);
			cartItemVo.setIsChecked(Const.CartChecked.CHECKED);
			cartItemVo.setAmount(amount);
			/*cartItemVo.getProduct().setStock(null);*/
			
			cartItemVos.add(cartItemVo);
			return true;
		}
		return false;
		}else{
			List<CartItemVo> cartItemVos = cartVo.getCartItemVos();
			for (CartItemVo item : cartItemVos) {
				if (isChecked != null) {
					if (isChecked) {
						item.setIsChecked(Const.CartChecked.CHECKED);
					} else {
						item.setIsChecked(Const.CartChecked.UNCHECKED);
					}
				}
			}
			return true;
			}
	}
	private void setCartVoToCookie(HttpServletResponse response, CartVo cartVo) {
		//将cartVo对象以json形式放到cookie
		StringWriter stringWriter = new StringWriter();
		ObjectMapper objectMapper = new ObjectMapper();
		//只有对象中不为null才转换
		objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		try {
			objectMapper.writeValue(stringWriter, cartVo);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//将购物车json放到cookie
		Cookie cookie = new Cookie(CART_COOKIE, stringWriter.toString());
		//设置cookie的存储时间
		cookie.setMaxAge(60 * 60 * 24);//单位秒
		//设置cookie路径
		cookie.setPath("/");
		//将cookie发送到浏览器
		response.addCookie(cookie);
	}

}
