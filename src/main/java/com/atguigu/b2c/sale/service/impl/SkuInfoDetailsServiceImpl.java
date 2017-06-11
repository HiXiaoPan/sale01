package com.atguigu.b2c.sale.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.b2c.sale.bean.Sql_Sku_Details;
import com.atguigu.b2c.sale.bean.T_MALL_SKU;
import com.atguigu.b2c.sale.mapper.SkuInfoDetailsMapper;
import com.atguigu.b2c.sale.service.SkuInfoDetailsService;
@Service
public class SkuInfoDetailsServiceImpl implements SkuInfoDetailsService {
	@Autowired
	SkuInfoDetailsMapper skuInfoDetailsMapper;
	@Override
	public Sql_Sku_Details getSkuInfoDetails(Map<String , Object> param) {
		
		return skuInfoDetailsMapper.select_sku_attr_product_by_skuid_shpid(param);
	}
	@Override
	public List<T_MALL_SKU> getSkuByshpid(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return skuInfoDetailsMapper.selectSkuByshpid(param);
	}


}
