package com.atguigu.b2c.sale.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.atguigu.b2c.sale.bean.Order_With_OrderInfo;
import com.atguigu.b2c.sale.bean.T_MALL_ORDER_INFO;
import com.atguigu.b2c.sale.bean.T_MALL_SHOPPINGCAR;
import com.atguigu.b2c.sale.service.OrderService;
import com.atguigu.b2c.sale.service.ShoppingCertService;
import com.atguigu.b2c.sale.utils.OrderDate;
import com.atguigu.b2c.ws.bean.T_MALL_ADDRESS;
import com.atguigu.b2c.ws.bean.T_MALL_USER;
import com.atguigu.b2c.ws.service.UserService;

@RequestMapping("/order")
@Controller
@SessionAttributes("orders")
public class OrderCotroller {
	@Autowired
	OrderService OrderServiceImpl;
	@Autowired
	UserService myUserService;
	@Autowired
	ShoppingCertService shoppingCertServiceImpl;
	@RequestMapping("dopay.htm")
	public String payOrder(HttpSession session,@ModelAttribute("orders") List<Order_With_OrderInfo> ordersWithOrderInfo) {
		try {
			OrderServiceImpl.change_order(ordersWithOrderInfo);
			session.removeAttribute("orders");
			return "sale/orderPaySuccess";
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/goto_error.do";
		}
		
		
	}
	
	@RequestMapping("goto_pay_order.htm")
	public String goto_pay_order(ModelMap map,@ModelAttribute("orders") List<Order_With_OrderInfo> ordersWithOrderInfo) {
		BigDecimal totalMoney = new BigDecimal("0");
		for (Order_With_OrderInfo order_With_OrderInfo : ordersWithOrderInfo) {
			totalMoney = totalMoney.add(order_With_OrderInfo.getZje());
		}
		map.put("orders", ordersWithOrderInfo);
		map.put("zje", totalMoney);
		return "sale/payOrder";
	}
	
	@RequestMapping(value="addOrder.htm",method=RequestMethod.POST)
	public String addOrder(@ModelAttribute("orders") List<Order_With_OrderInfo> ordersWithOrderInfo,
			Integer dzh_id,String dzh_mch,String shhr,String lxfsh,HttpSession session) {
		//通常将参数的封装在 Controller 中进行  添加订单的所有操作是一个事务，放在一个service 方法中！！！
		for (Order_With_OrderInfo order_With_OrderInfo : ordersWithOrderInfo) {
			//向订单中加入 地址id 地址名称 收货人 订单进度号，预计送达时间
			order_With_OrderInfo.setDzh_id(dzh_id);
			order_With_OrderInfo.setDzh_mch(dzh_mch);
			order_With_OrderInfo.setShhr(shhr);
			order_With_OrderInfo.setJdh(1);
			order_With_OrderInfo.setLxfsh(lxfsh);
			order_With_OrderInfo.setYjsdshj(OrderDate.get_yjsdshj(3));
		}
		try {
			OrderServiceImpl.addOrder(ordersWithOrderInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return "redirect:/goto_error.do";
		}
		//删除了购物车需要同步db跟session
		T_MALL_USER user = (T_MALL_USER)session.getAttribute("user");
		List<T_MALL_SHOPPINGCAR> db_list = shoppingCertServiceImpl.get_shopping_car_by_userid(user.getId());
		session.setAttribute("session_shopping_car", db_list);
		return "redirect:/order/goto_pay_order.htm";
	}
	
	@RequestMapping("/order.htm")
	public String doOrder(HttpSession session , ModelMap map) {
		T_MALL_USER user = (T_MALL_USER)session.getAttribute("user");
		List<T_MALL_SHOPPINGCAR> shopcars = null;
		if(user == null) {
			return "redirect:/user/login_buy.htm";
		}else {
			//用户已登录 ---> 选择被勾选的购物车，查出对应地址，根据地址创建订单
			// 获取用户收货地址
			List<T_MALL_ADDRESS> addr_user = myUserService.get_addr_by_user_id(user);
			shopcars = (List<T_MALL_SHOPPINGCAR>)session.getAttribute("session_shopping_car");
			List<Integer> checkedSkuIds = new ArrayList<>();
			List<Order_With_OrderInfo> ordersWithOrderInfo = new ArrayList<>();
			if(shopcars != null) {
				for (T_MALL_SHOPPINGCAR shoppingCar : shopcars) {
					if(shoppingCar.getShfxz().equals("1")) {
						checkedSkuIds.add(shoppingCar.getSku_id());
					}
				}
				//获取被选中 购物车的库存地址
				List<String> kcdzsOfSkuid = OrderServiceImpl.getKcdzBySkuIds(checkedSkuIds);
				for (String kcdz : kcdzsOfSkuid) {
					Order_With_OrderInfo orderWithOrderInfo = new Order_With_OrderInfo();
					
					List<T_MALL_ORDER_INFO> order_info_list =  new ArrayList<T_MALL_ORDER_INFO>();
					BigDecimal order_total_money = new BigDecimal("0");
					//添加订单中的订单详情
					for (T_MALL_SHOPPINGCAR shoppingCar : shopcars) {
						if(shoppingCar.getShfxz().equals("1")) {
							
							if(shoppingCar.getKcdz().equals(kcdz)) {
								T_MALL_ORDER_INFO orderInfo = new T_MALL_ORDER_INFO();
								orderInfo.setGwch_id(shoppingCar.getId());
								orderInfo.setShp_tp(shoppingCar.getShp_tp());
								orderInfo.setSku_id(shoppingCar.getSku_id());
								orderInfo.setSku_jg(shoppingCar.getSku_jg());
								orderInfo.setSku_mch(shoppingCar.getSku_mch());
								orderInfo.setSku_shl(shoppingCar.getTjshl());
								orderInfo.setSku_kcdz(shoppingCar.getKcdz());
								order_total_money = order_total_money.add(shoppingCar.getHj());
								order_info_list.add(orderInfo);
							}
						}
					}
					//订单详情添加到 orderWithOrderInfo 表
					orderWithOrderInfo.setOrder_info_list(order_info_list);
					orderWithOrderInfo.setZje(order_total_money);
					orderWithOrderInfo.setYh_id(user.getId());
					
					ordersWithOrderInfo.add(orderWithOrderInfo);
				}
				
			}
			map.put("addr_user", addr_user);
			map.put("orders", ordersWithOrderInfo);
			return "sale/OrderDetails";
		}
		
	}
}
