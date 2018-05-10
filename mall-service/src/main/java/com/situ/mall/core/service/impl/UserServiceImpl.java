package com.situ.mall.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.situ.mall.common.response.ServerResponse;
import com.situ.mall.common.response.util.MD5Util;
import com.situ.mall.core.entity.User;
import com.situ.mall.core.mapper.UserMapper;
import com.situ.mall.core.service.IUserService;
@Service
public class UserServiceImpl implements IUserService{
    @Autowired
    private UserMapper userMapper;
	@Override
	public ServerResponse<User> login(String username, String password) {
		int resultCount = userMapper.checkUsername(username);
		if (resultCount == 0) {
			return ServerResponse.createError("用户名不存在");
		}
		String md5Password = MD5Util.EncodeUtf8(password);
		User user = userMapper.selectLogin(username,password);
		if (user == null) {
			return ServerResponse.createError("密码错误");
		}
		//user.setPassword(StringUtils.EMPTY);
		 user.setPassword("");
		return ServerResponse.createSuccess("登陆成功", user);
	}
	@Override
	public ServerResponse<List<User>> pageList(Integer page, Integer limit,User username) {
		PageHelper.startPage(page, limit);
 		//数据data
		System.out.println(username);
 		List<User> list = userMapper.pageList(username);
 		//count
 		/*Integer count = (int) ((Page)list).getTotal();*/
 		Integer count = (int) ((Page) list).getTotal();
 		return ServerResponse.createSuccess("查询成功", count, list);
	}
	@Override
	public ServerResponse deleteById(Integer id) {
		int count = userMapper.deleteByPrimaryKey(id);
		if (count > 0) {
			return ServerResponse.createSuccess("删除成功");
		}
		return ServerResponse.createError("删除失败");
	}
	@Override
	public ServerResponse deleteAll(String ids) {
		String[] idsArray = ids.split(",");
	    int count = userMapper.deleteAll(idsArray);
	    if(count == idsArray.length){
	    	return ServerResponse.createSuccess("批量删除成功");
	    }
		return ServerResponse.createError("批量删除失败");
	}
	@Override
	public ServerResponse add(User user) {
		int rowCount = userMapper.insert(user);
		if (rowCount > 0) {
			return ServerResponse.createSuccess("添加用户成功");
		}
		return ServerResponse.createError("添加用户失败");
	}
	@Override
	public User selectById(Integer id) {
		return userMapper.selectByPrimaryKey(id);
	}
	@Override
	public ServerResponse update(User user) {
		int rowCount = userMapper.updateByPrimaryKeySelective(user);
		if (rowCount > 0) {
			return ServerResponse.createSuccess("更新用户成功");
		}
		return ServerResponse.createError("更新用户失败");
	}

}
