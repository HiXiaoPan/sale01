package com.atguigu.b2c.sale.bean;

import java.util.List;
/**
 * 
 * @author LvJinPing
 *分类属性与属性值联合查询
 */
public class T_MALL_ATTR_CONTAIN_VALUE extends T_MALL_ATTR {
	private List<T_MALL_VALUE> values ;

	public List<T_MALL_VALUE> getValues() {
		return values;
	}

	public void setValues(List<T_MALL_VALUE> values) {
		this.values = values;
	}
}
