package com.carqi.warehouse.entity;

import java.io.Serializable;

/**
 * 求租客户实体类 Class Name: RentClientEntity.java
 * 
 * @author Gao XuYang DateTime 2013-11-25 下午3:49:17
 * @version 1.0
 * @company 长春优狐科技开发有限公司
 */
public class RentClientEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	/* 联系人id */
	private Integer clientId;
	/* 求租客户姓名 */
	private String req_name;
	/* 全拼 */
	private String allLetter;
	/* 拼音首字母 */
	private String fristLetter;
	/* 拼音首字母 */
	private String sex;
	/* 客户手机号 */
	private String tel;
	/* 客户QQ */
	private String qq;
	/* 客户微信 */
	private String weixin;
	/* 客户来源 */
	private String clientResouce;
	/* 求租房屋类型 */
	private String houseType;
	/* 几室 */
	private String howRoom;
	/* 几厅 */
	private String howParlor;
	/* 价格上限 */
	private String priceUp;
	/* 价格下限 */
	private String priceDown;
	/* 价格区间*/
	private String priceRangeTxt;
	/* 厅室显示 例：3室1厅 */
	private String roomParlorTxt;
	/* 房屋用途（房屋类型） */
	private int purpose;
	/* 区域*/
	private String district;
	/* 区域Code*/
	private String districtCode;
	/* 区域关联关系*/
	private String path;
	/* 拼接区域显示 例：南关区-人民广场*/
	private String showDistrictTxt;
	/* 片区*/
	private String village;
	/* 备注 */
	private String remark;
	/* 朝向Code*/
	private String directionCode;
	/* 朝向*/
	private String direction;
	/* 客户类型 */
	private int type;
	/* 配置*/
	private String config;
	/* 配置Code*/
	private String configCode;
	/* 楼层下限*/
	private String floor_down;
	/* 楼层上限*/
	private String floor_up;
	/* 拼接楼层显示 例：1/5*/
	private String showFloorTxt;
	/* 是否为公盘 0为否，1为是*/
	private String isPublic;
	
	
	/* json返回数据info */
	private String retInfo;
	/* json返回数据状态 */
	private int retStatus;
	
	
	

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public String getReq_name() {
		return req_name;
	}

	public void setReq_name(String req_name) {
		this.req_name = req_name;
	}

	public String getAllLetter() {
		return allLetter;
	}

	public void setAllLetter(String allLetter) {
		this.allLetter = allLetter;
	}

	public String getFristLetter() {
		return fristLetter;
	}

	public void setFristLetter(String fristLetter) {
		this.fristLetter = fristLetter;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getWeixin() {
		return weixin;
	}

	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}

	public String getClientResouce() {
		return clientResouce;
	}

	public void setClientResouce(String clientResouce) {
		this.clientResouce = clientResouce;
	}

	
	public String getHouseType() {
		return houseType;
	}

	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}
	
	public String getHowRoom() {
		return howRoom;
	}

	public void setHowRoom(String howRoom) {
		this.howRoom = howRoom;
	}

	public String getHowParlor() {
		return howParlor;
	}

	public void setHowParlor(String howParlor) {
		this.howParlor = howParlor;
	}
	
	public String getPriceUp() {
		return priceUp;
	}

	public void setPriceUp(String priceUp) {
		this.priceUp = priceUp;
	}

	public String getPriceDown() {
		return priceDown;
	}

	public void setPriceDown(String priceDown) {
		this.priceDown = priceDown;
	}

	public int getPurpose() {
		return purpose;
	}

	public void setPurpose(int purpose) {
		this.purpose = purpose;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getRetInfo() {
		return retInfo;
	}

	public void setRetInfo(String retInfo) {
		this.retInfo = retInfo;
	}

	public int getRetStatus() {
		return retStatus;
	}

	public void setRetStatus(int retStatus) {
		this.retStatus = retStatus;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}


	public String getDirectionCode() {
		return directionCode;
	}

	public void setDirectionCode(String directionCode) {
		this.directionCode = directionCode;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public String getVillage() {
		return village;
	}

	public void setVillage(String village) {
		this.village = village;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getShowDistrictTxt() {
		return showDistrictTxt;
	}

	public void setShowDistrictTxt(String showDistrictTxt) {
		this.showDistrictTxt = showDistrictTxt;
	}

	public String getRoomParlorTxt() {
		return roomParlorTxt;
	}

	public void setRoomParlorTxt(String roomParlorTxt) {
		this.roomParlorTxt = roomParlorTxt;
	}

	public String getPriceRangeTxt() {
		return priceRangeTxt;
	}

	public void setPriceRangeTxt(String priceRangeTxt) {
		this.priceRangeTxt = priceRangeTxt;
	}

	public String getConfig() {
		return config;
	}

	public void setConfig(String config) {
		this.config = config;
	}

	public String getConfigCode() {
		return configCode;
	}

	public void setConfigCode(String configCode) {
		this.configCode = configCode;
	}

	public String getFloor_down() {
		return floor_down;
	}

	public void setFloor_down(String floor_down) {
		this.floor_down = floor_down;
	}

	public String getFloor_up() {
		return floor_up;
	}

	public void setFloor_up(String floor_up) {
		this.floor_up = floor_up;
	}

	public String getShowFloorTxt() {
		return showFloorTxt;
	}

	public void setShowFloorTxt(String showFloorTxt) {
		this.showFloorTxt = showFloorTxt;
	}

	public String getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(String isPublic) {
		this.isPublic = isPublic;
	}

	
	
}
