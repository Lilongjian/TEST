package com.situ.mall.portal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.situ.mall.common.response.ServerResponse;
import com.situ.mall.core.entity.Category;
import com.situ.mall.core.service.ICategoryService;

@Controller
public class IndexController {
	@Autowired
    private ICategoryService categoryService;
	@RequestMapping("/index")
	public String index(Model model){
		List<Category> category = categoryService.selectCategory();
		//category.get(1).getName();
		model.addAttribute("category", category);
		/*for (Category category2 : category) {
			System.out.println(category2.getId());
		}*/
		List<Category> categorytwo = categoryService.selectAll();
		model.addAttribute("categorytwo", categorytwo);
		return "index";
	}
	/*@RequestMapping("/selectSecondCategory")
    @ResponseBody
    public ServerResponse selectSecondCategory(Integer topCategoryId){
		System.out.println(topCategoryId);
   	 return categoryService.selectSecondCategory(topCategoryId);
    }*/

}
