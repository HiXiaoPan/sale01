package com.atguigu.b2c.sale.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.b2c.sale.bean.Datas;
import com.atguigu.b2c.sale.bean.T_MALL_ATTR_CONTAIN_VALUE;
import com.atguigu.b2c.sale.bean.T_MALL_CLASS_2;
import com.atguigu.b2c.sale.bean.UNION_SKU_SHP_TM;
import com.atguigu.b2c.sale.mapper.SearchMapper;
import com.atguigu.b2c.sale.service.SortSkuService;
@Service
public class SortSkuServiceImpl implements SortSkuService {
	@Autowired
	SearchMapper searchMapper;

	@Override
	public List<T_MALL_ATTR_CONTAIN_VALUE> get_attrAndValue_by_fl2id(T_MALL_CLASS_2 class2) {
		
		return searchMapper.select_attrAndValue_by_fl2id(class2.getId());
	}

	/*@Override
	public List<UNION_SKU_SHP_TM> get_sku_shp_tm_by_fl2id_attrids_attrValueids(Datas d) {
		// TODO Auto-generated method stub
		return searchMapper.select_sku_shp_tm_by_fl2id_attrids_attrValueids(d);
	}*/
	public List<UNION_SKU_SHP_TM> get_sku_shp_tm_by_fl2id_attrids_attrValueids(Datas d,String sql) {

		// 动态根据二级分类id和属性和属性值id拼接sku_id子查询的sql语句
		StringBuffer sbf = new StringBuffer();
		sbf.append("");
		List<Map<String,Integer>> attrAndValueids = d.getAttrAndValueids();
		if (attrAndValueids != null && attrAndValueids.size() > 0) {

			sbf.append(" AND a.id IN ");

			sbf.append("(");

			sbf.append(" select sku_0.sku_id from ");

			for (int i = 0; i < attrAndValueids.size(); i++) {
				sbf.append(" (select sku_id from t_mall_sku_attr_value where shxm_id = "
						+ attrAndValueids.get(i).get("shxm_id") + " and shxzh_id = "
						+ attrAndValueids.get(i).get("shxzh_id") + ") sku_" + i);
				if (i != (attrAndValueids.size() - 1)) {
					sbf.append(" , ");
				}
			}

			if (attrAndValueids.size() > 1) {
				sbf.append(" where ");
			}

			for (int i = 0; i < attrAndValueids.size(); i++) {
				if (i < (attrAndValueids.size() - 1) && i > 0) {
					sbf.append(" and ");
				}
				if (i < (attrAndValueids.size() - 1)) {
					sbf.append(" sku_" + i + ".sku_id = sku_" + (i + 1) + ".sku_id ");
				}

			}

			sbf.append(")");
		}

		HashMap<String, Object> hashMap = new HashMap<String, Object>();

		hashMap.put("class_2_id", d.getFlbh2id());

		hashMap.put("attr_value_sql", sbf.toString());
		hashMap.put("sql", sql);

		return searchMapper.select_sku_shp_tm_by_fl2id_attrids_attrValueids(hashMap);

	}
}
