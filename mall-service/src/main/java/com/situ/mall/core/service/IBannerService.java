package com.situ.mall.core.service;

import java.util.List;

import com.situ.mall.common.response.ServerResponse;
import com.situ.mall.core.entity.Banner;

public interface IBannerService {

	ServerResponse<List<Banner>> pageList(Integer page, Integer limit, Banner banner);

	/*ServerResponse deleteById(Integer id);

	ServerResponse deleteAll(String ids);

	ServerResponse add(Banner banner);

	Banner selectById(Integer id);

	ServerResponse update(Banner banner);

	List<Banner> selectByIdInfo(Integer categoryId);*/

}
