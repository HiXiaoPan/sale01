package com.atguigu.b2c.sale.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.b2c.sale.bean.Order_With_OrderInfo;
import com.atguigu.b2c.sale.bean.T_MALL_FLOW;
import com.atguigu.b2c.sale.bean.T_MALL_ORDER_INFO;
import com.atguigu.b2c.sale.bean.T_MALL_SKU;
import com.atguigu.b2c.sale.mapper.OrderMapper;
import com.atguigu.b2c.sale.service.OrderService;
import com.atguigu.b2c.sale.utils.OrderDate;
@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	OrderMapper orderMapper;

	@Override
	public List<String> getKcdzBySkuIds(List<Integer> checkedSkuIds) {
		return orderMapper.getKcdzBySkuIds(checkedSkuIds);
	}

	

	@Override
	public void addOrder_Info(Map<String, Object> param) {
		orderMapper.insertOrder_Info(param);
	}


	//已经在 spring配置文件中配置了事务
	@Override
	public void addOrder(List<Order_With_OrderInfo> ordersWithOrderInfo) {
		//4 个操作  创建订单表，创建订单信息表，删除购物车表相关数据，创建物流表。
		Map<String,Object> param = new HashMap<>();
		for (Order_With_OrderInfo order_With_OrderInfo : ordersWithOrderInfo) {
			orderMapper.insertOrder(order_With_OrderInfo); //插入order表并返回主键
			param.put("dd_id", order_With_OrderInfo.getId());
			param.put("order_info", order_With_OrderInfo.getOrder_info_list());
			orderMapper.insertOrder_Info(param);//插入order_info表
			// 删除购物车相应信息。
			param.put("yh_id", order_With_OrderInfo.getYh_id());
			orderMapper.delete_shoppingCar_by_userid_skuid_in_orderinfo(param);
			//插入物流表数据 一条订单信息对应一条物流信息
			T_MALL_FLOW flow = new T_MALL_FLOW();
			flow.setDd_id( order_With_OrderInfo.getId());
			flow.setMdd(order_With_OrderInfo.getDzh_mch());
			flow.setYh_id(order_With_OrderInfo.getYh_id());
			flow.setPsfsh("东风快递");
			flow.setPsmsh("送您回家");
			orderMapper.insert_flow(flow);
		}
	}



	@Override
	public void change_order(List<Order_With_OrderInfo> ordersWithOrderInfo) {
		//查出sku 中的库存数量进行对比 1判断库存是否足够，库存足够再进行支付，支付成功后修改销量与库存
		boolean flag = false;
		T_MALL_SKU sku = null;
		for (Order_With_OrderInfo order_With_OrderInfo : ordersWithOrderInfo) {
			for ( T_MALL_ORDER_INFO info : order_With_OrderInfo.getOrder_info_list()) {
				//查询时候加行锁
				sku =orderMapper.select_skukc_by_skuid(info.getSku_id());
				if(sku.getKc() < info.getSku_shl()) {
					flag = true;
					break;
				}
			}
			if(flag) {
				break;
			}
		}
		if(flag) {
			System.out.println(sku.getSku_mch()+"库存不足");
		}else {
			for (Order_With_OrderInfo order_With_OrderInfo : ordersWithOrderInfo) {
				order_With_OrderInfo.setJdh(2);
				//更新进度号，更新物流表by dd_id，修改sku库存和销量
				orderMapper.update_Order_jdh_by_ddid(order_With_OrderInfo);
				T_MALL_FLOW flow = new T_MALL_FLOW();
				flow.setDd_id(order_With_OrderInfo.getId());
				flow.setPsshj(OrderDate.get_yjsdshj(0));
				flow.setMqdd("待出库");
				orderMapper.update_flow_by_ddid(flow);
				//这里需要判断库存是否足够
				for ( T_MALL_ORDER_INFO info : order_With_OrderInfo.getOrder_info_list()) {
					orderMapper.update_sku_kc_xl_by_order_info(info);
				}
			}
		}
	}
}
