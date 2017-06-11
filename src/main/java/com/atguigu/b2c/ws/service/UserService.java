package com.atguigu.b2c.ws.service;

import java.util.List;

import javax.jws.WebService;

import com.atguigu.b2c.ws.bean.T_MALL_ADDRESS;
import com.atguigu.b2c.ws.bean.T_MALL_USER;
@WebService
public interface UserService {
	public void setDataSourceKey(String dataSourceKey);
	T_MALL_USER login(T_MALL_USER user);
	public boolean regist();

	public boolean update_user(T_MALL_USER user);

	public List<T_MALL_ADDRESS> get_addr_by_user_id(T_MALL_USER user);

	public int add_addresses(List<T_MALL_ADDRESS> addresses);

	public int update_addresses(T_MALL_ADDRESS address);
}
