package com.atguigu.b2c.sale.bean;

public class UNION_SKU_SHP_TM extends T_MALL_SKU{
	private T_MALL_PRODUCT product;
	private T_MALL_TRADE_MARK tm;
	public T_MALL_PRODUCT getProduct() {
		return product;
	}
	public void setProduct(T_MALL_PRODUCT product) {
		this.product = product;
	}
	public T_MALL_TRADE_MARK getTm() {
		return tm;
	}
	public void setTm(T_MALL_TRADE_MARK tm) {
		this.tm = tm;
	}
	
}
