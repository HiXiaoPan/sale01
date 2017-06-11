package com.atguigu.b2c.sale.controller;

import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.atguigu.b2c.sale.service.LoginService;
import com.atguigu.b2c.sale.service.ShoppingCertService;
import com.atguigu.b2c.sale.utils.JsonUtils;
import com.atguigu.b2c.sale.utils.MD5Utils;
import com.atguigu.b2c.sale.utils.MyUserService_ws;
import com.atguigu.b2c.sale.utils.ShoppingCarUtils;
import com.atguigu.b2c.ws.bean.T_MALL_USER;
import com.atguigu.b2c.ws.service.UserService;
import com.google.gson.Gson;
@RequestMapping("/user")
@Controller
public class LoginController {
	@Autowired
	LoginService loginServiceImpl;
	@Autowired 
	ShoppingCertService shoppingCertServiceImpl;
	@Autowired
	UserService myUserService;
	
	@RequestMapping("/logout.htm")
	public String userLogout(HttpSession session,HttpServletResponse res) {
		session.invalidate();
		Cookie cook = new Cookie("userCookie","");
		cook.setMaxAge(0);
		cook.setPath("/");
		res.addCookie(cook);
		return "saleIndex";
	}
	@RequestMapping("login_buy.htm")
	public String goto_login_by() {
		return"sale/login_buy";
	}
	/**
	 * 订单提交用户登录
	 * @return 返回到OrderController中的/order.htm中
	 */
	@RequestMapping(value="/login_buy.htm" ,method=RequestMethod.POST)
	public String login_buy(T_MALL_USER user,String autoLogin,HttpSession session,HttpServletResponse res,
			HttpServletRequest req,ModelMap map ,@CookieValue(value="cookie_shopping_car",required=false) String json_cookie_shopping_car) {
		user.setYh_mm(MD5Utils.md5(user.getYh_mm()));
		myUserService.setDataSourceKey("1");
		T_MALL_USER checkedUser = myUserService.login(user);
		//T_MALL_USER checkedUser = loginServiceImpl.login(user);
		Gson gson = new Gson();
		if(checkedUser != null) {
			session.setAttribute("user",  checkedUser);
			ShoppingCarUtils.cookieStringToDbAndSession_loginBuy(checkedUser.getId(), json_cookie_shopping_car, session, shoppingCertServiceImpl);
			//转移 cookie中数据后删除 cookie
			Cookie cook = new Cookie("cookie_shopping_car","");
			cook.setMaxAge(0);
			cook.setPath("/");
			res.addCookie(cook);
			//7天内自动登录
			if("1".equals(autoLogin)) {
				String userJson = gson.toJson(checkedUser);
				String encode = null;
				Cookie cookie = null;
				try {
					encode = URLEncoder.encode(userJson, "utf-8");
					cookie = new Cookie("userCookie",encode);
				    cookie.setPath("/");
				    cookie.setMaxAge(60*60*24*7);
				    res.addCookie(cookie);
				    
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			
			return "redirect:/order/order.htm";
		}else {
			map.put("err",  "用户名或密码错误");
			return "sale/login_buy";
		}
	}
	
	@RequestMapping("/xxx/login.htm")
	public String userLogin(T_MALL_USER user,String autoLogin,HttpSession session,HttpServletResponse res,
			HttpServletRequest req,ModelMap map ,@CookieValue(value="cookie_shopping_car",required=false) String json_cookie_shopping_car) {
		user.setYh_mm(MD5Utils.md5(user.getYh_mm()));
		myUserService.setDataSourceKey("1");
		T_MALL_USER checkedUser = myUserService.login(user);
		//T_MALL_USER checkedUser = loginServiceImpl.login(user);
		Gson gson = new Gson();
		if(checkedUser != null) {
			session.setAttribute("user",  checkedUser);
			ShoppingCarUtils.cookieStringToDbAndSession(checkedUser.getId(), json_cookie_shopping_car, session, shoppingCertServiceImpl);
			//转移 cookie中数据后删除 cookie
			Cookie cook = new Cookie("cookie_shopping_car","");
			cook.setMaxAge(0);
			cook.setPath("/");
			res.addCookie(cook);
			//7天内自动登录
			if("1".equals(autoLogin)) {
				String userJson = gson.toJson(checkedUser);
				String encode = null;
				Cookie cookie = null;
				try {
					encode = URLEncoder.encode(userJson, "utf-8");
					cookie = new Cookie("userCookie",encode);
				    cookie.setPath(req.getContextPath());
				    cookie.setMaxAge(60*60*24*7);
				    res.addCookie(cookie);
				    
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			
			return "saleIndex";
		}else {
			map.put("err",  "用户名或密码错误");
			return "sale/login";
		}
		
	}
	@RequestMapping("/toLogin.htm")
	public String toLogin() {
		return "sale/login";
	}
	
	
}
