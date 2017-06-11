package com.atguigu.b2c.sale.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.atguigu.b2c.sale.bean.Datas;
import com.atguigu.b2c.sale.bean.T_MALL_ATTR_CONTAIN_VALUE;
import com.atguigu.b2c.sale.bean.T_MALL_CLASS_2;
import com.atguigu.b2c.sale.bean.UNION_SKU_SHP_TM;
import com.atguigu.b2c.sale.service.SortSkuService;
import com.atguigu.b2c.sale.utils.JedisPoolUtils;
import com.atguigu.b2c.sale.utils.JsonUtils;

import redis.clients.jedis.Jedis;
@RequestMapping("/sort")
@Controller
public class SortSkuController {

	@Autowired
	SortSkuService sortSkuServiceImpl;
	
	
	@RequestMapping("get_sku_info.do")
	public String get_sku_info(Datas d,String sql,ModelMap map) {
		//从redis 中拿数据了
		/*List<UNION_SKU_SHP_TM> union =
				sortSkuServiceImpl.get_sku_shp_tm_by_fl2id_attrids_attrValueids(d,sql);*/
		List<UNION_SKU_SHP_TM> union = new ArrayList<UNION_SKU_SHP_TM>();
		String valueFromRedis = null;
		Jedis jedis = JedisPoolUtils.getJedis();
		if(d.getAttrAndValueids() == null || d.getAttrAndValueids().size() == 0) {
			String class2_redis_name = "class2_skuShpTm_redis_" + d.getFlbh2id();
			//排序，就那么几种方法，将不同排序的sku查出来放到redis中，根据价格，销量，创建时间等设置成score 即可啊！
			Set<String> zrange = jedis.zrange(class2_redis_name, 0, -1);//取出来的是一个什么hashset还是什么？
			Iterator<String> iterator = zrange.iterator();
			while(iterator.hasNext()) {
				 union.add(JsonUtils.jsonToObj(iterator.next(), UNION_SKU_SHP_TM.class));
			}
		}else {
			//执行根据属性的筛选操作
		}
		
		map.put("skuInfo",union);
		return "sale/SkuInfo";
	}
	
	@RequestMapping("/goto_product_page/{fl2id}/{fl2name}.htm")
	public String goto_product_page(@PathVariable String fl2name,@PathVariable Integer fl2id, ModelMap map) {
		T_MALL_CLASS_2 class2= new T_MALL_CLASS_2();
		class2.setId(fl2id);
		class2.setFlmch2(fl2name);
		List<T_MALL_ATTR_CONTAIN_VALUE> attrAndValue = sortSkuServiceImpl.get_attrAndValue_by_fl2id(class2);
		map.put("attrAndValue", attrAndValue);
		map.put("class2", class2);
		return "sale/SkuShowPage";
	}
}
