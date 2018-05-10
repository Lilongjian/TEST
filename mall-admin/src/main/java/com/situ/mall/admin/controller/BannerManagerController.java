package com.situ.mall.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.situ.mall.common.response.ServerResponse;
import com.situ.mall.core.entity.Banner;
import com.situ.mall.core.service.ICategoryService;
import com.situ.mall.core.service.IBannerService;

@Controller
@RequestMapping("/banner")
public class BannerManagerController {
	@Autowired
	private IBannerService bannerService;
	
	@RequestMapping("/pageList")
	 	@ResponseBody
	 	public ServerResponse<List<Banner>> pageList(Integer page, Integer limit,Banner banner) {
	 		return bannerService.pageList(page, limit,banner);
	 	}
	/*@RequestMapping("/deleteById")
	@ResponseBody
	public ServerResponse deleteById(Integer id){
		return bannerService.deleteById(id);
	}*/
	/*@RequestMapping("/deleteAll")
	@ResponseBody
	public ServerResponse deleteAll(String ids){
		return bannerService.deleteAll(ids);
	}*/
	 	
 	@RequestMapping("/getBannerPage")
 	public String getUserPage() {
 		return "banner_list";
 	}
 	@RequestMapping("/getAddPage")
 	public String getAddPage() {
 		return "banner_add";
 	}
 	/*@RequestMapping("/add")
 	@ResponseBody
 	public ServerResponse add(Banner banner){
 		return bannerService.add(banner);
 	}
 	
 	@RequestMapping("/getEditPage")
 	public String getEditPage(Integer id,Model model){
 		Banner banner = bannerService.selectById(id);
 		model.addAttribute("banner", banner);
 		Integer parentCategoryId = categoryService.selectParentCategoryId(banner.getCategoryId());
 		model.addAttribute("parentCategoryId", parentCategoryId);
 		return "banner_edit";
 	}
 	
 	@RequestMapping("/update")
 	@ResponseBody
 	public ServerResponse update(Banner banner){
 		return bannerService.update(banner);
 	}*/
	 	
	 
}
