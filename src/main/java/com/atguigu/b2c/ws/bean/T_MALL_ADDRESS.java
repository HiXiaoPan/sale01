package com.atguigu.b2c.ws.bean;

public class T_MALL_ADDRESS {
	private Integer    id , yh_id  ; 
	private String  yh_shhdz   , dzzt,  shjr ,lxfsh;
	public String getLxfsh() {
		return lxfsh;
	}
	public void setLxfsh(String lxfsh) {
		this.lxfsh = lxfsh;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getYh_id() {
		return yh_id;
	}
	public void setYh_id(Integer yh_id) {
		this.yh_id = yh_id;
	}
	public String getYh_shhdz() {
		return yh_shhdz;
	}
	public void setYh_shhdz(String yh_shhdz) {
		this.yh_shhdz = yh_shhdz;
	}
	public String getDzzt() {
		return dzzt;
	}
	public void setDzzt(String dzzt) {
		this.dzzt = dzzt;
	}
	public String getShjr() {
		return shjr;
	}
	public void setShjr(String shjr) {
		this.shjr = shjr;
	}
	
}
