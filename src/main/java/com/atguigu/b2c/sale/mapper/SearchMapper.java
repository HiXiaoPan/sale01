package com.atguigu.b2c.sale.mapper;

import java.util.HashMap;
import java.util.List;

import com.atguigu.b2c.sale.bean.Datas;
import com.atguigu.b2c.sale.bean.T_MALL_ATTR_CONTAIN_VALUE;
import com.atguigu.b2c.sale.bean.UNION_SKU_SHP_TM;

public interface SearchMapper {
	List<UNION_SKU_SHP_TM> select_sku_shp_tm_by_fl2id_attrids_attrValueids_err(Datas d);
	List<T_MALL_ATTR_CONTAIN_VALUE> select_attrAndValue_by_fl2id(Integer fl2id);
	List<UNION_SKU_SHP_TM> select_sku_shp_tm_by_fl2id_attrids_attrValueids(HashMap<String , Object> map);
}
