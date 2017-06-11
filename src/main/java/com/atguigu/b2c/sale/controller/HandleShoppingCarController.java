package com.atguigu.b2c.sale.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.atguigu.b2c.sale.bean.T_MALL_SHOPPINGCAR;
import com.atguigu.b2c.ws.bean.T_MALL_USER;
import com.atguigu.b2c.sale.service.ShoppingCertService;
import com.atguigu.b2c.sale.utils.JsonUtils;
@RequestMapping("/handleShoppingCar")
@Controller
public class HandleShoppingCarController {
	
	@Autowired
	ShoppingCertService shoppingCertServiceImpl;
	
	@RequestMapping("/update_number.do")
	public String update_number(Integer tjshl,Integer carid,Integer skuid,String shfxz,ModelMap map,HttpSession session,
			@CookieValue(value="cookie_shopping_car",required=false) String json_cookie_shopping_car,
			HttpServletResponse res) {
		T_MALL_USER user = (T_MALL_USER)session.getAttribute("user");
		List<T_MALL_SHOPPINGCAR> shopcars = null;
		if(user != null && user.getId()!=0) {
			shopcars = (List<T_MALL_SHOPPINGCAR>)session.getAttribute("session_shopping_car");
			for (T_MALL_SHOPPINGCAR shopcar : shopcars) {
				if(shopcar.getId()==carid) {
					shopcar.setShfxz(shfxz);
					shopcar.setTjshl(tjshl);
					shopcar.setHj(shopcar.getSku_jg().multiply(new BigDecimal(shopcar.getTjshl()+"")));
					shoppingCertServiceImpl.update_Shopping_car_shfxz_hj(shopcar);
				}
			}
		}else {
			shopcars = JsonUtils.jsonToList(json_cookie_shopping_car, T_MALL_SHOPPINGCAR.class);
			for (T_MALL_SHOPPINGCAR shopcar : shopcars) {
				if(shopcar.getSku_id()==skuid) {
					shopcar.setShfxz(shfxz);
					shopcar.setTjshl(tjshl);
					shopcar.setHj(shopcar.getSku_jg().multiply(new BigDecimal(shopcar.getTjshl()+"")));
				}
			}
			Cookie cook = new Cookie("cookie_shopping_car",JsonUtils.listToJson(shopcars));
			cook.setMaxAge(60*60*7*24);
			cook.setPath("/");
			res.addCookie(cook);
		}
		BigDecimal car_sum_checked = new BigDecimal("0");
		for (T_MALL_SHOPPINGCAR shopcar : shopcars) {
			if(shopcar.getShfxz().equals("1")) {
				car_sum_checked = car_sum_checked.add(shopcar.getHj());
			}
		}
		map.put("car_sum_checked", car_sum_checked);
		map.put("car_list", shopcars);
		return "sale/TotalInnerCar";
	}
	
	@RequestMapping(value="if_checked_car.do",method=RequestMethod.POST)
	public String if_checked_car(Integer carid,Integer skuid,String shfxz,ModelMap map,HttpSession session,
			@CookieValue(value="cookie_shopping_car",required=false) String json_cookie_shopping_car,
			HttpServletResponse res) {
		T_MALL_USER user = (T_MALL_USER)session.getAttribute("user");
		List<T_MALL_SHOPPINGCAR> shopcars = null;
		if(user != null && user.getId()!=0) {
			shopcars = (List<T_MALL_SHOPPINGCAR>)session.getAttribute("session_shopping_car");
			for (T_MALL_SHOPPINGCAR shopcar : shopcars) {
				if(shopcar.getId()==carid) {
					shopcar.setShfxz(shfxz);
					shoppingCertServiceImpl.update_Shopping_car_shfxz(shopcar);
				}
			}
		}else {
			shopcars = JsonUtils.jsonToList(json_cookie_shopping_car, T_MALL_SHOPPINGCAR.class);
			for (T_MALL_SHOPPINGCAR shopcar : shopcars) {
				if(shopcar.getSku_id()==skuid) {
					shopcar.setShfxz(shfxz);
				}
			}
			Cookie cook = new Cookie("cookie_shopping_car",JsonUtils.listToJson(shopcars));
			cook.setMaxAge(60*60*7*24);
			cook.setPath("/");
			res.addCookie(cook);
		}
		BigDecimal car_sum_checked = new BigDecimal("0");
		for (T_MALL_SHOPPINGCAR shopcar : shopcars) {
			if(shopcar.getShfxz().equals("1")) {
				car_sum_checked = car_sum_checked.add(shopcar.getHj());
			}
		}
		map.put("car_sum_checked", car_sum_checked);
		map.put("car_list", shopcars);
		return "sale/TotalInnerCar";
	}
	
	@RequestMapping(value = "/get_total_inner.do",method=RequestMethod.GET )
	public String get_inner_car(ModelMap map,HttpSession session,
			@CookieValue(value="cookie_shopping_car",required=false) String json_cookie_shopping_car) {
		T_MALL_USER user = (T_MALL_USER)session.getAttribute("user");
		List<T_MALL_SHOPPINGCAR> shopcars = null;
		if(user == null || user.getId() == 0) {
			if(json_cookie_shopping_car == null || json_cookie_shopping_car.equals("")) {
				shopcars = new ArrayList<>();
			}else {
				shopcars = JsonUtils.jsonToList(json_cookie_shopping_car, T_MALL_SHOPPINGCAR.class);
			}
		}else {
			shopcars = (List<T_MALL_SHOPPINGCAR>)session.getAttribute("session_shopping_car");
			if(shopcars == null) {
				shopcars = new ArrayList<>();
			}
		}
		BigDecimal car_sum_checked = new BigDecimal("0");
		for (T_MALL_SHOPPINGCAR shopcar : shopcars) {
			if(shopcar.getShfxz()!=null) {
				if(shopcar.getShfxz().equals("1")) {
					car_sum_checked = car_sum_checked.add(shopcar.getHj());
				}
			}
		}
		map.put("car_sum_checked", car_sum_checked);
		map.put("car_list", shopcars);
		return "sale/TotalInnerCar";
	}
	
	@RequestMapping("/goto_total_shoppingCar.htm")
	public String goto_total_shoppingCar() {
		return "sale/TotalShoppingCar";
	}
}
