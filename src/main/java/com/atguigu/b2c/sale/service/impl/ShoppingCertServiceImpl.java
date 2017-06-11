package com.atguigu.b2c.sale.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.b2c.sale.bean.T_MALL_SHOPPINGCAR;
import com.atguigu.b2c.sale.mapper.ShoppingCertMapper;
import com.atguigu.b2c.sale.service.ShoppingCertService;
@Service
public class ShoppingCertServiceImpl implements ShoppingCertService {
	@Autowired
	ShoppingCertMapper shoppingCertMapper;
	@Override
	public int add_Shopping_car(T_MALL_SHOPPINGCAR doingshoppingCar) {
		return shoppingCertMapper.insert_shoppingcar(doingshoppingCar);
	}
	@Override
	public int update_Shopping_car(T_MALL_SHOPPINGCAR doingshoppingCar) {
		// TODO Auto-generated method stub
		return shoppingCertMapper.update_shoppingcar(doingshoppingCar);
	}
	@Override
	public List<T_MALL_SHOPPINGCAR> get_shopping_car_by_userid(Integer userid) {
		// TODO Auto-generated method stub
		return shoppingCertMapper.select_shopping_car_by_userid( userid);
	}
	@Override
	public void update_Shopping_car_shfxz(T_MALL_SHOPPINGCAR shopcar) {
		shoppingCertMapper.update_Shopping_car_shfxz(shopcar);
		
	}
	@Override
	public void update_Shopping_car_shfxz_hj(T_MALL_SHOPPINGCAR shopcar) {
		shoppingCertMapper.update_Shopping_car_shfxz_hj(shopcar);
		
	}
	@Override
	public void set_shoppingCar_unchecked_by_userid(Integer userid) {
		shoppingCertMapper.update_shoppingCar_unchecked_by_userid(userid);
	}

}
