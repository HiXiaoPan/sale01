package com.atguigu.b2c.sale.mapper;

import java.util.List;

import com.atguigu.b2c.sale.bean.T_MALL_SHOPPINGCAR;

public interface ShoppingCertMapper {

	int insert_shoppingcar(T_MALL_SHOPPINGCAR doingshoppingCar);

	int update_shoppingcar(T_MALL_SHOPPINGCAR doingshoppingCar);

	List<T_MALL_SHOPPINGCAR> select_shopping_car_by_userid(Integer userid);

	void update_Shopping_car_shfxz(T_MALL_SHOPPINGCAR shopcar);

	void update_Shopping_car_shfxz_hj(T_MALL_SHOPPINGCAR shopcar);

	void update_shoppingCar_unchecked_by_userid(Integer userid);
	
	
}
