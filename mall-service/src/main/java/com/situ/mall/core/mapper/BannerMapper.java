package com.situ.mall.core.mapper;

import java.util.List;

import com.situ.mall.core.entity.Banner;

public interface BannerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Banner record);

    int insertSelective(Banner record);

    Banner selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Banner record);

    int updateByPrimaryKey(Banner record);

	List<Banner> pageList(Banner banner);
}