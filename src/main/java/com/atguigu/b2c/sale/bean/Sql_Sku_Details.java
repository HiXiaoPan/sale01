package com.atguigu.b2c.sale.bean;

import java.util.List;

public class Sql_Sku_Details extends T_MALL_SKU{
	// 用于封装 从 数据库中返回的对象 用sql 前缀
	// 用于封装 页面传递过来的参数的 bean jsp 前缀
	private List<T_MALL_PRODUCT_IMAGE> imgs ;
	private List<Sql_Attrvalue_Attr_OnebyOne> attrAndValue;
	
	public List<Sql_Attrvalue_Attr_OnebyOne> getAttrAndValue() {
		return attrAndValue;
	}
	public void setAttrAndValue(List<Sql_Attrvalue_Attr_OnebyOne> attrAndValue) {
		this.attrAndValue = attrAndValue;
	}
	public List<T_MALL_PRODUCT_IMAGE> getImgs() {
		return imgs;
	}
	public void setImgs(List<T_MALL_PRODUCT_IMAGE> imgs) {
		this.imgs = imgs;
	}
	
}
