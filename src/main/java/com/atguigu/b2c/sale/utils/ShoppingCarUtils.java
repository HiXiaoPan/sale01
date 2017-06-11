package com.atguigu.b2c.sale.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.atguigu.b2c.sale.bean.T_MALL_SHOPPINGCAR;
import com.atguigu.b2c.sale.service.ShoppingCertService;

public class ShoppingCarUtils {
	/**
	 *订单提交登录操作后将 cookie 中购物车的数据更新到 session与db 中 
	 */
	public static void cookieStringToDbAndSession_loginBuy(Integer userid,String carJson,HttpSession session,ShoppingCertService shoppingCertServiceImpl) {
		shoppingCertServiceImpl.set_shoppingCar_unchecked_by_userid(userid);
		List<T_MALL_SHOPPINGCAR> db_list = shoppingCertServiceImpl.get_shopping_car_by_userid(userid);
		List<T_MALL_SHOPPINGCAR> cookie_list = null;
			try {
				carJson = URLDecoder.decode(carJson, "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			cookie_list = JsonUtils.jsonToList(carJson, T_MALL_SHOPPINGCAR.class);
			
				for (int i = 0; i < cookie_list.size(); i++) {
					Map<String,Object> param = if_old_car(db_list,cookie_list.get(i));
					cookie_list.get(i).setYh_id(userid);
					if((boolean) param.get("b")) {
						T_MALL_SHOPPINGCAR sameCar = (T_MALL_SHOPPINGCAR)param.get("db_car");
						sameCar.setTjshl(cookie_list.get(i).getTjshl());
						sameCar.setHj(sameCar.getSku_jg().multiply(new BigDecimal(sameCar.getTjshl())));
						sameCar.setShfxz(cookie_list.get(i).getShfxz());
						shoppingCertServiceImpl.update_Shopping_car_shfxz_hj(sameCar);
					}else {
						shoppingCertServiceImpl.add_Shopping_car(cookie_list.get(i));
					}
				}
			
		db_list = shoppingCertServiceImpl.get_shopping_car_by_userid(userid);
		session.setAttribute("session_shopping_car", db_list);
		
	}
	/**
	 *用户登录成功后将 cookie 中购物车数据更新到 db与session 中的购物车数据 
	 */
	public static void cookieStringToDbAndSession(Integer userid,String carJson,HttpSession session,ShoppingCertService shoppingCertServiceImpl) {
		List<T_MALL_SHOPPINGCAR> db_list = shoppingCertServiceImpl.get_shopping_car_by_userid(userid);
		List<T_MALL_SHOPPINGCAR> cookie_list = null;
		if(carJson == null || carJson.equals("")) {
			
		}else {
			try {
				carJson = URLDecoder.decode(carJson, "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			cookie_list = JsonUtils.jsonToList(carJson, T_MALL_SHOPPINGCAR.class);
			if(cookie_list == null || cookie_list.size()<=0) {
			}else {
				for (int i = 0; i < cookie_list.size(); i++) {
					Map<String,Object> param = if_old_car(db_list,cookie_list.get(i));
					cookie_list.get(i).setYh_id(userid);
					if((boolean) param.get("b")) {
						T_MALL_SHOPPINGCAR sameCar = (T_MALL_SHOPPINGCAR)param.get("db_car");
						sameCar.setTjshl(sameCar.getTjshl()+cookie_list.get(i).getTjshl());
						sameCar.setHj(sameCar.getSku_jg().multiply(new BigDecimal(sameCar.getTjshl())));
						shoppingCertServiceImpl.update_Shopping_car(sameCar);
					}else {
						shoppingCertServiceImpl.add_Shopping_car(cookie_list.get(i));
					}
				}
			}
		}
		
		db_list = shoppingCertServiceImpl.get_shopping_car_by_userid(userid);
		session.setAttribute("session_shopping_car", db_list);
		
	}
	
	public static Map<String,Object> if_old_car(List<T_MALL_SHOPPINGCAR> db_list,T_MALL_SHOPPINGCAR cookie_shoppingcar) {
		Map<String,Object> param = new HashMap<String, Object>();
		boolean b = false;
		for (int i = 0; i < db_list.size(); i++) {
			if(db_list.get(i).getSku_id() == cookie_shoppingcar.getSku_id()) {
				param.put("db_car", db_list.get(i));
				b = true;
			}
		}
		param.put("b", b);
		return param;
	}
}	
