package com.situ.mall.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.situ.mall.common.response.ServerResponse;
import com.situ.mall.core.entity.Banner;
import com.situ.mall.core.mapper.BannerMapper;
import com.situ.mall.core.service.IBannerService;
@Service
public class BannerServiceImpl implements IBannerService{
    @Autowired
    private BannerMapper bannerMapper;
	@Override
	public ServerResponse<List<Banner>> pageList(Integer page, Integer limit,Banner banner) {
		PageHelper.startPage(page, limit);
 		//数据data
 		List<Banner> list = bannerMapper.pageList(banner);
 		//count
 		/*Integer count = (int) ((Page)list).getTotal();*/
 		Integer count = (int) ((Page) list).getTotal();
 		//第二种：用PageInfo对结果进行包装
 		/* PageInfo pageInfo = new PageInfo(list);
 		 Integer count = (int) pageInfo.getTotal();*/
 		return ServerResponse.createSuccess("查询成功", count, list);
	}
	/*@Override
	public ServerResponse deleteById(Integer id) {
		int count = bannerMapper.deleteByPrimaryKey(id);
		if (count > 0) {
			return ServerResponse.createSuccess("删除成功");
		}
		return ServerResponse.createError("删除失败");
	}
	@Override
	public ServerResponse deleteAll(String ids) {
		String[] idsArray = ids.split(",");
	    int count = bannerMapper.deleteAll(idsArray);
	    if(count == idsArray.length){
	    	return ServerResponse.createSuccess("批量删除成功");
	    }
		return ServerResponse.createError("批量删除失败");
	}
	@Override
	public ServerResponse add(Banner banner) {
		int rowCount = bannerMapper.insert(banner);
		if (rowCount > 0) {
			return ServerResponse.createSuccess("添加商品成功");
		}
		return ServerResponse.createError("添加商品失败");
	}
	@Override
	public Banner selectById(Integer id) {
		return bannerMapper.selectByPrimaryKey(id);
	}
	@Override
	public ServerResponse update(Banner banner) {
		int rowCount =  bannerMapper.updateBy PrimaryKeySelective(banner);
		if (rowCount > 0) {
			return ServerResponse.createSuccess("更新商品成功");
		}
		return ServerResponse.createError("更新商品失败");
	}
	@Override
	public List<Banner> selectByIdInfo(Integer categoryId) {
		return bannerMapper.selectByPrimaryCategoryId(categoryId);
	}*/

}
