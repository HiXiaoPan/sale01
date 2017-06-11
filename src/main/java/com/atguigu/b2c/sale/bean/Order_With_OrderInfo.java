package com.atguigu.b2c.sale.bean;

import java.util.List;

public class Order_With_OrderInfo extends T_MALL_ORDER{
	private List<T_MALL_ORDER_INFO> order_info_list ;

	public List<T_MALL_ORDER_INFO> getOrder_info_list() {
		return order_info_list;
	}

	public void setOrder_info_list(List<T_MALL_ORDER_INFO> order_info_list) {
		this.order_info_list = order_info_list;
	}
	
}
