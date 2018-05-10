package com.situ.mall.portal.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.situ.mall.common.response.ServerResponse;
import com.situ.mall.core.constant.Const;
import com.situ.mall.core.entity.User;
import com.situ.mall.core.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private IUserService userService;
	@RequestMapping("/getLoginPage")
	public String getLoginPage(){
		return "login";
	}
 @RequestMapping(value="/login",method=RequestMethod.POST)
 @ResponseBody
 public ServerResponse login(String username,String password,HttpSession session){
	 ServerResponse<User> response = userService.login(username, password);
	 User user = response.getData();
	 session.setAttribute(Const.CURRENT_USER,user);
	 return response; 
 }
 @RequestMapping("/getLoginInspect")
 @ResponseBody
      public ServerResponse getLoginInspect(HttpSession session){
       User user = (User) session.getAttribute("CURRENT_USER");
       if (user != null) {
            return ServerResponse.createSuccess("已经登录");
      }
            return ServerResponse.createError("请登录");
      }
 @RequestMapping("/loginOut")
      public String loginOut(HttpSession session){
	  session.removeAttribute("CURRENT_USER");
	  return "loginMain";
 }

}
