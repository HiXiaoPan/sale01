package com.atguigu.b2c.sale.utils;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.wss4j.common.ext.WSPasswordCallback;

public class MallSecurityCallback_Client implements CallbackHandler {

	@Override
	public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
		WSPasswordCallback  callback =(WSPasswordCallback )callbacks[0];
		callback.setIdentifier("ljp");
		callback.setPassword("12345");
	}

}
