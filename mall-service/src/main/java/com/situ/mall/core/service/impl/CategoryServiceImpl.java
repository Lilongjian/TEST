package com.situ.mall.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.situ.mall.common.response.ServerResponse;
import com.situ.mall.core.entity.Category;
import com.situ.mall.core.entity.User;
import com.situ.mall.core.mapper.CategoryMapper;
import com.situ.mall.core.service.ICategoryService;
import com.situ.mall.core.vo.CategoryCountVo;

@Service
public class CategoryServiceImpl implements ICategoryService{
	@Autowired
	private CategoryMapper categoryMapper;

	@Override
	public ServerResponse selectTopCategory() {
		List<Category> list = categoryMapper.selectTopCategory();
		if (list == null || list.size() == 0) {
			return ServerResponse.createError("没有一级分类");
		}
		for (Category category : list) {
			System.out.println("查询出来的数据" + category);
		}
		return ServerResponse.createSuccess("查找一级分类成功",list);
	}

	@Override
	public ServerResponse selectSecondCategory(Integer topCategoryId) {
		List<Category> list = categoryMapper.selectSecondCategory(topCategoryId);
		if (list == null || list.size() == 0) {
			return ServerResponse.createSuccess("没有二级分类");
		}
		return ServerResponse.createSuccess("查找二级分类成功",list);
	}

	@Override
	public Integer selectParentCategoryId(Integer categoryId) {
		return categoryMapper.selectParentCategoryId(categoryId);
	}

	@Override
	public ServerResponse<List<Category>> pageList(Integer page, Integer limit, Category name) {
		PageHelper.startPage(page, limit);
 		//数据data
		System.out.println(name);
 		List<Category> list = categoryMapper.pageList(name);
 		//count
 		/*Integer count = (int) ((Page)list).getTotal();*/
 		Integer count = (int) ((Page) list).getTotal();
 		return ServerResponse.createSuccess("查询成功", count, list);
	}

	@Override
	public ServerResponse deleteById(Integer id) {
		 int count = categoryMapper.deleteByPrimaryKey(id);
		 if (count > 0) {
			return ServerResponse.createSuccess("删除成功");
		}
		 return ServerResponse.createError("删除失败");
	}

	@Override
	public ServerResponse add(Category category) {
		int rowCount = categoryMapper.insert(category);
		if (rowCount > 0) {
			return ServerResponse.createSuccess("添加用户成功");
		}else {
			return ServerResponse.createError("添加用户失败");
		}
	}

	@Override
	public ServerResponse deleteAll(String ids) {
		String[] idsArray = ids.split(",");
	    int count = categoryMapper.deleteAll(idsArray);
	    if(count == idsArray.length){
	    	return ServerResponse.createSuccess("批量删除成功");
	    }
		return ServerResponse.createError("批量删除失败");
	}

	@Override
	public Category selectById(Integer id) {
		return categoryMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Category> selectCategory() {
		List<Category> list = categoryMapper.selectTopCategory();
		return list;
	}

	@Override
	public List<Category> selectAll() {
		return categoryMapper.selectAll();
	}

	@Override
	public ServerResponse getCategoryCountAnalysis() {
		List<CategoryCountVo> list = categoryMapper.getCategoryCountAnalysis();
		if (list == null || list.size() == 0) {
			return ServerResponse.createError("查找失败或数据为空");
		}
		return ServerResponse.createSuccess("查找成功", list);
	}

	@Override
	public List<Category> getCategoryTwo(Integer parentId) {
		return categoryMapper.selectSecondCategory(parentId);
	}

}
