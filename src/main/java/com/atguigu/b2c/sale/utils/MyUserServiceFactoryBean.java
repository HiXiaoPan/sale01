package com.atguigu.b2c.sale.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.apache.cxf.ws.security.wss4j.WSS4JOutInterceptor;
import org.apache.ws.security.handler.WSHandlerConstants;
import org.springframework.beans.factory.FactoryBean;

public class MyUserServiceFactoryBean<T> implements FactoryBean<T> {
	private String url;
	private Class<T> t;
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Class<T> getT() {
		return t;
	}

	public void setT(Class<T> t) {
		this.t = t;
	}

	@Override
	public T getObject() throws Exception {
		JaxWsProxyFactoryBean wsProxyFactoryBean = new JaxWsProxyFactoryBean();
		Map<String, Object> map = new HashMap<String, Object>();

		map.put(WSHandlerConstants.ACTION, WSHandlerConstants.USERNAME_TOKEN);
		map.put(WSHandlerConstants.PASSWORD_TYPE, "PasswordText");
		map.put(WSHandlerConstants.PW_CALLBACK_CLASS, MallSecurityCallback_Client.class.getName());
		map.put(WSHandlerConstants.USER, "username");

		WSS4JOutInterceptor wss4jOutInterceptor = new WSS4JOutInterceptor(map);
		wsProxyFactoryBean.getOutInterceptors().add(wss4jOutInterceptor);
		wsProxyFactoryBean.setAddress(this.url);
		return wsProxyFactoryBean.create(this.t);
	}

	@Override
	public Class<T> getObjectType() {
		// TODO Auto-generated method stub
		return this.t;
	}

	@Override
	public boolean isSingleton() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
