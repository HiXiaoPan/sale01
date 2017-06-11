package com.atguigu.b2c.sale.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.atguigu.b2c.sale.bean.Sql_Sku_Details;
import com.atguigu.b2c.sale.bean.T_MALL_SKU;


public interface SkuInfoDetailsService {

	Sql_Sku_Details getSkuInfoDetails(Map<String , Object> param);

	List<T_MALL_SKU> getSkuByshpid(Map<String, Object> param);

}
