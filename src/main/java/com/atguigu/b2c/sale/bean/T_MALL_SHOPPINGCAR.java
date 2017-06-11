package com.atguigu.b2c.sale.bean;

import java.math.BigDecimal;
import java.util.Date;

public class T_MALL_SHOPPINGCAR {
	private Integer id,tjshl,yh_id , shp_id  ,sku_id;
	private String sku_mch,shp_tp  ,shfxz ,kcdz  ;
	public String getKcdz() {
		return kcdz;
	}
	public void setKcdz(String kcdz) {
		this.kcdz = kcdz;
	}
	private BigDecimal sku_jg,hj;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getTjshl() {
		return tjshl;
	}
	public void setTjshl(Integer tjshl) {
		this.tjshl = tjshl;
	}
	public Integer getYh_id() {
		return yh_id;
	}
	public void setYh_id(Integer yh_id) {
		this.yh_id = yh_id;
	}
	public Integer getShp_id() {
		return shp_id;
	}
	public void setShp_id(Integer shp_id) {
		this.shp_id = shp_id;
	}
	public Integer getSku_id() {
		return sku_id;
	}
	public void setSku_id(Integer sku_id) {
		this.sku_id = sku_id;
	}
	public String getSku_mch() {
		return sku_mch;
	}
	public void setSku_mch(String sku_mch) {
		this.sku_mch = sku_mch;
	}
	public String getShp_tp() {
		return shp_tp;
	}
	public void setShp_tp(String shp_tp) {
		this.shp_tp = shp_tp;
	}
	public String getShfxz() {
		return shfxz;
	}
	public void setShfxz(String shfxz) {
		this.shfxz = shfxz;
	}
	public BigDecimal getSku_jg() {
		return sku_jg;
	}
	public void setSku_jg(BigDecimal sku_jg) {
		this.sku_jg = sku_jg;
	}
	public BigDecimal getHj() {
		return hj;
	}
	public void setHj(BigDecimal hj) {
		this.hj = hj;
	}
	public Date getChjshj() {
		return chjshj;
	}
	public void setChjshj(Date chjshj) {
		this.chjshj = chjshj;
	}
	private Date chjshj;
	
}
