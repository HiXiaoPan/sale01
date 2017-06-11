package com.atguigu.b2c.sale.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.atguigu.b2c.sale.bean.T_MALL_SHOPPINGCAR;
import com.atguigu.b2c.ws.bean.T_MALL_USER;
import com.atguigu.b2c.sale.service.ShoppingCertService;
import com.atguigu.b2c.sale.utils.JsonUtils;
@RequestMapping("/shoppingCert")
@Controller
public class ShoppingCertController {
	
	@Autowired
	ShoppingCertService shoppingCertServiceImpl;
	
	@RequestMapping(value = "/get_inner_car.do")
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
		map.put("car_list", shopcars);
		return "sale/innerCar";
	}
	
	@RequestMapping(value="/goto_shoppingCert.htm",method=RequestMethod.POST)
	public ModelAndView goto_shoppingCert(T_MALL_SHOPPINGCAR doingshoppingCar,HttpServletResponse reps,
			HttpSession session,HttpServletRequest rqe ,@CookieValue(value="cookie_shopping_car",required=false) String json_cookie_shopping_car ) {
		
		T_MALL_USER getUserAtOnce = (T_MALL_USER)session.getAttribute("user");
		List<T_MALL_SHOPPINGCAR> shopcars = null;
		
		//未登录 放 cookie 中，因为需要将购物车中的信息保留一段时间，浏览器关闭后再次打开需要还有此信息.
		if(getUserAtOnce == null || getUserAtOnce.getId() == 0) {
			
			if(json_cookie_shopping_car != null && !json_cookie_shopping_car.equals("")) {
				//此cookie存在   判断添加的数据是新数据还是老数据
				shopcars = JsonUtils.jsonToList(json_cookie_shopping_car, T_MALL_SHOPPINGCAR.class);
				
				boolean b = if_old_car(shopcars,doingshoppingCar);
				if(b) {
					for (int i = 0; i < shopcars.size(); i++) {
						if(shopcars.get(i).getSku_id() == doingshoppingCar.getSku_id()) {
						//老数据	改价格 改添加数量
							shopcars.get(i).setTjshl(shopcars.get(i).getTjshl() + doingshoppingCar.getTjshl());
							shopcars.get(i).setHj(shopcars.get(i)
									.getSku_jg().multiply(new BigDecimal(shopcars.get(i).getTjshl()+"")));
						}
					}
				}else {
					doingshoppingCar.setHj(doingshoppingCar.getSku_jg());
					shopcars.add(doingshoppingCar);
				}
				
			}else {
				//new 一个 集合 查出 sku 对应到 shoppingcar 中的信息添加 add 进集合 并放进 cookie 中
				shopcars = new ArrayList<>();
				doingshoppingCar.setHj(doingshoppingCar.getSku_jg());
				shopcars.add(doingshoppingCar);
				
			}
			String list_to_json_cookie_shopping_car = JsonUtils.listToJson(shopcars);
			Cookie cookie = new Cookie("cookie_shopping_car",list_to_json_cookie_shopping_car);
			cookie.setMaxAge(60*60*24*7);
			cookie.setPath("/");
			reps.addCookie(cookie);
			
		}else {
	//已登录 在执行登录操作时将 db 中的数据拿出来放在 session 中。
			shopcars = (List<T_MALL_SHOPPINGCAR>)session.getAttribute("session_shopping_car");
			doingshoppingCar.setYh_id(getUserAtOnce.getId());
			if(shopcars == null ||  shopcars.size()<=0) {  //session_shopping_car 不存在直接添加
				shopcars = new ArrayList<>();
				doingshoppingCar.setHj(doingshoppingCar.getSku_jg());
				try {
					int row = shoppingCertServiceImpl.add_Shopping_car(doingshoppingCar);
					if(row == 1) {
						shopcars.add(doingshoppingCar);
					}
					session.setAttribute("session_shopping_car",shopcars);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}else {
				boolean b =  if_old_car(shopcars,doingshoppingCar);
				if(b) {
					//更新数据库
					for (int i = 0; i < shopcars.size(); i++) {
						if(shopcars.get(i).getSku_id() == doingshoppingCar.getSku_id()) {
							shopcars.get(i).setTjshl(doingshoppingCar.getTjshl()+shopcars.get(i).getTjshl());
							shopcars.get(i).setHj(shopcars.get(i).getSku_jg().multiply(new BigDecimal(shopcars.get(i).getTjshl()+"")));
							doingshoppingCar = shopcars.get(i);
							shoppingCertServiceImpl.update_Shopping_car(shopcars.get(i));
						}
					}
					
				}else {
					try {
						doingshoppingCar.setHj(doingshoppingCar.getSku_jg());
						int row = shoppingCertServiceImpl.add_Shopping_car(doingshoppingCar);
						if( row == 1) {
							shopcars.add(doingshoppingCar);
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
			}
		}
		ModelAndView mav = new ModelAndView("redirect:/shoppingCert/goto_show_car/{sku_mch}/{sku_id}/{tjshl}.htm");
		mav.addObject("sku_mch", doingshoppingCar.getSku_mch());
		mav.addObject("sku_id", doingshoppingCar.getSku_id());
		mav.addObject("tjshl", doingshoppingCar.getTjshl());
		return mav;
	}
	
	@RequestMapping("/goto_show_car/{sku_mch}/{sku_id}/{tjshl}.htm")
	public String goto_show_car(ModelMap map,@PathVariable String sku_mch,@PathVariable Integer sku_id,@PathVariable Integer tjshl ) {
		map.put("sku_mch", sku_mch);
		map.put("sku_id", sku_id);
		map.put("tjshl", tjshl);
		return "sale/ShoppingCert";
	}
	
	public static boolean if_old_car(List<T_MALL_SHOPPINGCAR> listCar,T_MALL_SHOPPINGCAR car) {
		boolean b = false;
		for (int i = 0; i < listCar.size(); i++) {
			if(listCar.get(i).getSku_id() == car.getSku_id()) {
				b = true;
			}
		}
		
		return b;
	}
}
