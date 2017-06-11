package com.atguigu.b2c.sale.utils;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;


public class MyUserService_ws {
	
	public static <T> T getUserServiceImpl(String url,Class<T> t) {
		JaxWsProxyFactoryBean wsProxyFactoryBean = new JaxWsProxyFactoryBean();
		wsProxyFactoryBean.setAddress(url);
		return wsProxyFactoryBean.create(t);
	}
	
}
