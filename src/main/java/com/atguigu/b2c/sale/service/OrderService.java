package com.atguigu.b2c.sale.service;

import java.util.List;
import java.util.Map;

import com.atguigu.b2c.sale.bean.Order_With_OrderInfo;

public interface OrderService {

	List<String> getKcdzBySkuIds(List<Integer> checkedSkuIds);

	void addOrder(List<Order_With_OrderInfo> ordersWithOrderInfo);

	void addOrder_Info(Map<String, Object> param);

	void change_order(List<Order_With_OrderInfo> ordersWithOrderInfo);

}
