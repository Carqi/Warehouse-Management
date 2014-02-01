package com.carqi.warehouse.entity;

import java.io.Serializable;

/**
 * 货物实体类
 * @author Administrator
 * 2014-1-25 下午10:54:38
 */
public class GoodsEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/* 货品名称*/
	private String name;
	/* 货品型号*/
	private String model;
	/* 货品品牌*/
	private String brand;
	/* 类型 */
	private String type;
	/* 单价 */
	private String unit_price;
	/* 购买数量 */
	private String buy_num;
	/* 剩余数量 */
	private String now_num;
	/* 总价 */
	private String total_price;
	/* 购买人id*/
	private String buy_personid;
	/* 购买人*/
	private String buy_person;
	/* 供应商id*/
	private String supplierid;
	/* 购买时间*/
	private String buy_date;
	/* 入库时间*/
	private String in_date;
	/* 备注*/
	private String remark;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUnit_price() {
		return unit_price;
	}
	public void setUnit_price(String unit_price) {
		this.unit_price = unit_price;
	}
	public String getBuy_num() {
		return buy_num;
	}
	public void setBuy_num(String buy_num) {
		this.buy_num = buy_num;
	}
	public String getNow_num() {
		return now_num;
	}
	public void setNow_num(String now_num) {
		this.now_num = now_num;
	}
	public String getTotal_price() {
		return total_price;
	}
	public void setTotal_price(String total_price) {
		this.total_price = total_price;
	}
	public String getBuy_personid() {
		return buy_personid;
	}
	public void setBuy_personid(String buy_personid) {
		this.buy_personid = buy_personid;
	}
	public String getSupplierid() {
		return supplierid;
	}
	public void setSupplierid(String supplierid) {
		this.supplierid = supplierid;
	}
	public String getBuy_date() {
		return buy_date;
	}
	public void setBuy_date(String buy_date) {
		this.buy_date = buy_date;
	}
	public String getIn_date() {
		return in_date;
	}
	public void setIn_date(String in_date) {
		this.in_date = in_date;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getBuy_person() {
		return buy_person;
	}
	public void setBuy_person(String buy_person) {
		this.buy_person = buy_person;
	}
	
	
	
	
}
