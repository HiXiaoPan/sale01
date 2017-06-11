package com.atguigu.b2c.sale.mapper;

import java.util.List;
import java.util.Map;

import com.atguigu.b2c.sale.bean.Sql_Sku_Details;
import com.atguigu.b2c.sale.bean.T_MALL_SKU;

public interface SkuInfoDetailsMapper {

	Sql_Sku_Details select_sku_attr_product_by_skuid_shpid(Map<String, Object> param);

	List<T_MALL_SKU> selectSkuByshpid(Map<String, Object> param);

}
