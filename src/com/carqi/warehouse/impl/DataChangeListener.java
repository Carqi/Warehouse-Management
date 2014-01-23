package com.carqi.warehouse.impl;


 /**
 *  Class Name: DataChangeListener.java
 *  数据改变接口
 *  @author Yu Liu  DateTime 2013-11-8 上午8:56:10    
 *  @version 1.0
 *  @company 长春优狐科技开发有限公司
 */
public interface DataChangeListener {

	public void dataModify(String key, String value ,String code);
}
