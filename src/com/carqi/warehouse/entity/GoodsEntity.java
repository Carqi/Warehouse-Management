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
	private Integer type;
	/* 单价 */
	private float unit_price;
	/* 购买数量 */
	private Integer buy_num;
	/* 剩余数量 */
	private Integer now_num;
	/* 总价 */
	private float total_price;
	/* 购买人id*/
	private Integer buy_personid;
	/* 供应商id*/
	private Integer supplierid;
	/* 购买时间*/
	private Integer buy_date;
	/* 入库时间*/
	private Integer in_date;
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
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public float getUnit_price() {
		return unit_price;
	}
	public void setUnit_price(float unit_price) {
		this.unit_price = unit_price;
	}
	public Integer getBuy_num() {
		return buy_num;
	}
	public void setBuy_num(Integer buy_num) {
		this.buy_num = buy_num;
	}
	public Integer getNow_num() {
		return now_num;
	}
	public void setNow_num(Integer now_num) {
		this.now_num = now_num;
	}
	public float getTotal_price() {
		return total_price;
	}
	public void setTotal_price(float total_price) {
		this.total_price = total_price;
	}
	public Integer getBuy_personid() {
		return buy_personid;
	}
	public void setBuy_personid(Integer buy_personid) {
		this.buy_personid = buy_personid;
	}
	public Integer getSupplierid() {
		return supplierid;
	}
	public void setSupplierid(Integer supplierid) {
		this.supplierid = supplierid;
	}
	public Integer getBuy_date() {
		return buy_date;
	}
	public void setBuy_date(Integer buy_date) {
		this.buy_date = buy_date;
	}
	public Integer getIn_date() {
		return in_date;
	}
	public void setIn_date(Integer in_date) {
		this.in_date = in_date;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
