package com.atguigu.b2c.sale.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.atguigu.b2c.sale.bean.Sql_Sku_Details;
import com.atguigu.b2c.sale.bean.T_MALL_SKU;
import com.atguigu.b2c.sale.service.SkuInfoDetailsService;
@RequestMapping("/skuInfo")
@Controller
public class SkuInfoDetailsController {
	@Autowired
	SkuInfoDetailsService skuInfoDetailsServiceImpl;
	
	@RequestMapping(value="/skuInfo_goto_skuDetails/{skuid}/{shpid}.htm",method=RequestMethod.GET)
	public String get_sku_info_details(@PathVariable Integer skuid,@PathVariable Integer shpid,ModelMap map) {
		Map<String , Object> param = new HashMap<>();
		param.put("skuid", skuid);
		param.put("shpid", shpid);
		Sql_Sku_Details skuDetail = skuInfoDetailsServiceImpl.getSkuInfoDetails(param);
		List<T_MALL_SKU> skus =  skuInfoDetailsServiceImpl.getSkuByshpid(param);
		map.put("skus", skus);
		map.put("skuDetail", skuDetail);
		return "sale/SkuInfoDetails";
	}
	
	
}
