package com.atguigu.b2c.sale.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.b2c.sale.bean.T_MALL_USER;
import com.atguigu.b2c.sale.mapper.T_MALL_USER_MAPPER;
import com.atguigu.b2c.sale.service.LoginService;
@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
	T_MALL_USER_MAPPER t_MALL_USER_MAPPER;
	
	@Override
	public T_MALL_USER login(T_MALL_USER user) {
		
		return t_MALL_USER_MAPPER.selectUserBypwdAndUname(user);
	}

}
