package com.atguigu.b2c.sale.controller;

import java.net.URLDecoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atguigu.b2c.ws.bean.T_MALL_USER;
import com.atguigu.b2c.sale.service.ShoppingCertService;
import com.atguigu.b2c.sale.utils.ShoppingCarUtils;
import com.google.gson.Gson;
@RequestMapping("/saleIndex")
@Controller
public class SaleIndexController {

	@Autowired 
	ShoppingCertService shoppingCertServiceImpl;
	
	@RequestMapping("/index.do")
	public String index(HttpServletRequest req , HttpSession session,
			HttpServletResponse rps,@CookieValue(value="cookie_shopping_car",required=false) String json_cookie_shopping_car) {
		//上一次浏览器非直接关闭了，再次打开时服务器接到请求了，但是 getCoolies() 中会出现 null
		Cookie[] cookies = req.getCookies();
		if(cookies != null) {
			Gson gson = new Gson();
			for (int i = 0; i < cookies.length; i++) {
				if("userCookie".equals(cookies[i].getName())) {
					String decode = null;
					T_MALL_USER user=null;
					try {
						decode = URLDecoder.decode(cookies[i].getValue(),"UTF-8");
						user = gson.fromJson(decode, T_MALL_USER.class);
						session.setAttribute("user", user);
						ShoppingCarUtils.cookieStringToDbAndSession(user.getId(), json_cookie_shopping_car, session, shoppingCertServiceImpl);
						//转移 cookie中数据后删除 cookie
						Cookie cook = new Cookie("cookie_shopping_car","");
						cook.setMaxAge(0);
						cook.setPath("/");
						rps.addCookie(cook);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		return "saleIndex";
	}
}
