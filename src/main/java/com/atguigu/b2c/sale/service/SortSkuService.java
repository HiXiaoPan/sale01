package com.atguigu.b2c.sale.service;

import java.util.List;

import com.atguigu.b2c.sale.bean.Datas;
import com.atguigu.b2c.sale.bean.T_MALL_ATTR_CONTAIN_VALUE;
import com.atguigu.b2c.sale.bean.T_MALL_CLASS_2;
import com.atguigu.b2c.sale.bean.UNION_SKU_SHP_TM;

public interface SortSkuService {

	List<T_MALL_ATTR_CONTAIN_VALUE> get_attrAndValue_by_fl2id(T_MALL_CLASS_2 class2id);

	List<UNION_SKU_SHP_TM> get_sku_shp_tm_by_fl2id_attrids_attrValueids(Datas d,String sql);

}
