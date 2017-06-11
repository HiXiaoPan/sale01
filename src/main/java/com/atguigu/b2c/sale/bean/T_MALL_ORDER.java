package com.atguigu.b2c.sale.bean;

import java.math.BigDecimal;
import java.util.Date;

public class T_MALL_ORDER {
	private Integer     id, jdh ,  yh_id    ,  dzh_id    ;
	private BigDecimal zje;
	private String shhr,dzh_mch,lxfsh;
	public String getLxfsh() {
		return lxfsh;
	}
	public void setLxfsh(String lxfsh) {
		this.lxfsh = lxfsh;
	}
	private Date chjshj,yjsdshj;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getJdh() {
		return jdh;
	}
	public void setJdh(Integer jdh) {
		this.jdh = jdh;
	}
	public Integer getYh_id() {
		return yh_id;
	}
	public void setYh_id(Integer yh_id) {
		this.yh_id = yh_id;
	}
	public Integer getDzh_id() {
		return dzh_id;
	}
	public void setDzh_id(Integer dzh_id) {
		this.dzh_id = dzh_id;
	}
	public BigDecimal getZje() {
		return zje;
	}
	public void setZje(BigDecimal zje) {
		this.zje = zje;
	}
	public String getShhr() {
		return shhr;
	}
	public void setShhr(String shhr) {
		this.shhr = shhr;
	}
	public String getDzh_mch() {
		return dzh_mch;
	}
	public void setDzh_mch(String dzh_mch) {
		this.dzh_mch = dzh_mch;
	}
	public Date getChjshj() {
		return chjshj;
	}
	public void setChjshj(Date chjshj) {
		this.chjshj = chjshj;
	}
	public Date getYjsdshj() {
		return yjsdshj;
	}
	public void setYjsdshj(Date yjsdshj) {
		this.yjsdshj = yjsdshj;
	}
	
}
