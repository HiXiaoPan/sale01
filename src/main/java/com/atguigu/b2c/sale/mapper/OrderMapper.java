package com.atguigu.b2c.sale.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.atguigu.b2c.sale.bean.Order_With_OrderInfo;
import com.atguigu.b2c.sale.bean.T_MALL_FLOW;
import com.atguigu.b2c.sale.bean.T_MALL_ORDER_INFO;
import com.atguigu.b2c.sale.bean.T_MALL_SKU;

public interface OrderMapper {
	// 若使用 list，则list 的名字必须也用list 才能直接使用，不然就需要将 list 放在map 中或者使用 @param
	List<String> getKcdzBySkuIds(@Param("checkedSkuIds") List<Integer> checkedSkuIds);

	void insertOrder(Order_With_OrderInfo order_With_OrderInfo);

	void insertOrder_Info(Map<String, Object> param);

	void delete_shoppingCar_by_userid_skuid_in_orderinfo(Map<String, Object> param);

	void insert_flow(T_MALL_FLOW flow);

	void update_Order_jdh_by_ddid(Order_With_OrderInfo order_With_OrderInfo);

	void update_flow_by_ddid(T_MALL_FLOW flow);

	void update_sku_kc_xl_by_order_info(T_MALL_ORDER_INFO info);

	T_MALL_SKU select_skukc_by_skuid(Integer sku_id);

}
