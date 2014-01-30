package com.carqi.warehouse.entity;

import java.io.Serializable;

/**
 * 购买人实体类
 * @author Administrator
 * 2014-1-30 下午5:21:26
 */
public class BuyPersonEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/* id*/
	private String id;
	/* 姓名*/
	private String name;
	/* 联系电话*/
	private String tel;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
}
