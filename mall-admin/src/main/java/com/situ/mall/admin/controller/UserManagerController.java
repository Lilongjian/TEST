package com.situ.mall.admin.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.situ.mall.common.response.ServerResponse;
import com.situ.mall.core.constant.Const;
import com.situ.mall.core.entity.Product;
import com.situ.mall.core.entity.User;
import com.situ.mall.core.service.IUserService;

@Controller
@RequestMapping("/user")
public class UserManagerController {
	@Autowired
	private IUserService userService;
	
	@RequestMapping(value="/getLoginPage")
   public String getLoginPage(){
	   return "login";
	   //return "NewFile";
   }
	
	@RequestMapping("/pageList")
	 	@ResponseBody
	 	public ServerResponse<List<User>> pageList(Integer page, Integer limit,User username) {
	 		return userService.pageList(page, limit,username);
	 	}
	 	
	 	@RequestMapping("/getUserPage")
	 	public String getUserPage() {
	 		return "user_list";
	 	}
	 	
	 	@RequestMapping("/deleteById")
		@ResponseBody
		public ServerResponse deleteById(Integer id){
			return userService.deleteById(id);
		}
	 
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public ServerResponse login(String username,String password,HttpSession session){
		ServerResponse<User> response = userService.login(username,password);
		if (response.isSuccess()) {
			User user = response.getData();
			System.out.println("用户的信息" + user);
			if (user.getRole() == Const.Role.ROLE_ADMIN) {
				//说明确实是管理员
				session.setAttribute(Const.CURRENT_USER,user);
				return response;
			}else{
				return ServerResponse.createError("不是管理员，无法登陆");
			}
			
		}
		return response;
	}
	@RequestMapping("/deleteAll")
	@ResponseBody
	public ServerResponse deleteAll(String ids){
		return userService.deleteAll(ids);
	}
	
	@RequestMapping("/getAddPage")
 	public String getAddPage() {
 		return "user_add";
 	}
	@RequestMapping("/add")
 	@ResponseBody
 	public ServerResponse add(User user){
 		return userService.add(user);
 	}
	@RequestMapping("/getEditPage")
 	public String getEditPage(Integer id,Model model){
 		User user = userService.selectById(id);
 		model.addAttribute("user", user);
 		return "user_edit";
 	}
	@RequestMapping("/update")
 	@ResponseBody
 	public ServerResponse update(User user){
 		return userService.update(user);
 	}
	
}
