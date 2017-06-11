package com.atguigu.b2c.sale.service;

import java.util.List;

import com.atguigu.b2c.sale.bean.T_MALL_SHOPPINGCAR;

public interface ShoppingCertService {

	int add_Shopping_car(T_MALL_SHOPPINGCAR doingshoppingCar);

	int update_Shopping_car(T_MALL_SHOPPINGCAR doingshoppingCar);

	List<T_MALL_SHOPPINGCAR> get_shopping_car_by_userid(Integer userid);

	void update_Shopping_car_shfxz(T_MALL_SHOPPINGCAR shopcar);

	void update_Shopping_car_shfxz_hj(T_MALL_SHOPPINGCAR shopcar);

	void set_shoppingCar_unchecked_by_userid(Integer userid);

}
