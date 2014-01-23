package com.carqi.warehouse.core;

import java.util.HashMap;

import android.util.Log;

/**
 * Class Name: AppConfig.java 配置类
 * 
 * @author Yu Liu DateTime 2013-11-6 上午10:23:06
 * @version 1.0
 * @company 长春优狐科技开发有限公司
 */
public class AppConfig {
	public static String[] ori = new String[] { "东", "南", "西", "北", "东南", "西南", "西北","东北", "南北", "东西" };
	public static String[] decorate = new String[] { "毛坯房", "普通装修", "精装修", "豪华装修" };
	public static String[] houseType = new String[] { "住宅", "别墅", "写字楼", "商铺","车库" };
	public static String[] rentType = new String[] { "整租", "合租" };
	public static String[] payforType = new String[] { "押一付三", "押二付二", "押一付二", "押二付一", "押一付一", "年付不押", "半年付不押" };
	public static String[] houseConfi = new String[] { "水", "电", "煤气", "暖气", "有线电视", "宽带", "冰箱", "空调", "洗衣机", "热水器",
			"微波炉", "电话" };
	public static String[] area = new String[]{"不限","50㎡以下","50-80㎡","80-100㎡","100-130㎡","130-150㎡","150-200㎡","200-300㎡","300-500㎡","500㎡以上"};
	public static String[] areaNoAll = new String[]{"50㎡以下","50-80㎡","80-100㎡","100-130㎡","130-150㎡","150-200㎡","200-300㎡","300-500㎡","500㎡以上"};
	public static String[] areaValue = new String[]{"50","50-80","80-100","100-130","130-150","150-200","200-300","300-500","500"};
	public static String[] timeValue = new String[]{"今天","最近一周","最近一个月","最近三个月","具体时间"};
	
	/** 客户来源*/
	public static String[] clientResource = new String[] { "进店", "电话", "介绍", "网站", "报纸", "老客户", "扫街", "活动", "其它" };
		
	/** 租金范围*/
	public static String[]  rentRange = new String[] { "不限","500元以下", "500-1000元", "1000-1500元", "1500-2000元", "2000-3000元", "3000-4500元", "4500元以上" };
	public static String[]  rentRangeNoAll = new String[] { "500元以下", "500-1000元", "1000-1500元", "1500-2000元", "2000-3000元", "3000-4500元", "4500元以上" };
	public static String[]  rentRangeValue = new String[] { "500", "500-1000", "1000-1500", "1500-2000", "2000-3000", "3000-4500", "4500" };
	
	public static String[] sellRange = new String[] {"不限","50万以下","50-80万","80-100万","100-120万","120-150万","150万以上"};
	public static String[] sellRangeNoAll = new String[] {"50万以下","50-80万","80-100万","100-120万","120-150万","150万以上"};
	public static String[] sellRangeValue = new String[] {"50","50-80","80-100","100-120","120-150","150"};
	public static String[] room = new String[]{"不限","一室","两室","三室","四室","四室以上",};
	public static String[] searchType = new String[]{"出租","出售"};
	
	/****更多页---*/
	public static String[] moreName = { "个人信息", "公告", "设置", "备忘录", "房贷计算器", "关于" };
	/****更多页结束---*/
	/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~数据库操作~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
	/** 数据库文件名*/
	public static final String DB_FILE_NAME = "wjbroker_db";
	/** 数据库版本号*/
	public static final Integer DB_NOW_VERSION =2;
	/** 求租客户列表*/
	public static final String DB_RENT_LIST_TABLE = "rent_client_list";
	/** 求购客户列表*/
	public static final String DB_BUY_LIST_TABLE = "buy_client_list";
	/** 备忘录   表*/
	public static final String DB_MEMORANDUM_TABLE = "memo_randum";
	
	
	
	/*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~URL请求~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
	/** 服务器地址 */
	public static final String SERVER = "http://www.jouhu.com";
	//public static final String SERVER = "http://88.88.88.77";
	/** 文件地址 */
	public static final String FILE = "/wangju";
	/** 数据地址 */
	public static final String JSON = "/jmobile.php?s=";	
	
	public static final String PUBLIC = "/Public";
	/** 图片文件地址 */
	public static final String IMAGE_FILE = SERVER + FILE;
	/** 新版本地址 */
	public static final String APP_UPDATE_URL = SERVER + FILE;
	/** 登录地址 */ 
	public static final String LOGIN_URL = SERVER + FILE + JSON + PUBLIC + "/login/";
	/** 登出地址 */ 
	public static final String LOGOUT_URL = SERVER + FILE + JSON + PUBLIC + "/logout/";
	/** 求租客户列表 */ 
	public static final String RENT_CLIENT_LIST_URL = SERVER + FILE + JSON + "/RequestRentClient/";
	/** 添加求租客户 */ 
	public static final String ADD_RENT_CLIENT_URL = SERVER + FILE + JSON + "/RequestRentClient/insert/";
	/** 编辑求租客户 */ 
	public static final String MODIFY_RENT_CLIENT_URL = SERVER + FILE + JSON + "/RequestRentClient/modify/";
	
	/** 求租客户详细信息 */ 
	public static final String RENT_CLIENT_DETAIL_URL = SERVER + FILE + JSON + "/RequestRentClient/clientDetail/";
	
	
	/** 求购客户列表 */ 
	public static final String SALE_CLIENT_LIST_URL = SERVER + FILE + JSON + "/RequestBuyClient/";
	/** 添加求购客户 */ 
	public static final String ADD_BUY_CLIENT_URL = SERVER + FILE + JSON + "/RequestBuyClient/insert/";
	/** 编辑求购客户 */ 
	public static final String MODIFY_BUY_CLIENT_URL = SERVER + FILE + JSON + "/RequestBuyClient/modify/";
	/** 求购客户详细信息 */ 
	public static final String BUY_CLIENT_DETAIL_URL = SERVER + FILE + JSON + "/RequestBuyClient/clientDetail/";
	
	public static final String RENT_HOUSE_ADD = SERVER + FILE + JSON + "/RentHouse/insert/";
	/**获取区域*/
	public static final String GET_DISTRICT_URL = SERVER + FILE + JSON + "/AreaInfo/getArea/";
	/**获取区域*/
	public static final String GET_CELL_URL = SERVER + FILE + JSON + "/AreaInfo/getAreaChildren/";

	/**查询房源*/
	public static final String SEARCH_HOUSE_URL = SERVER + FILE + JSON + "/SearchHouse/";
	
	/**查询配置*/
	public static final String GET_RENT_HOUSE_MATCH = SERVER + FILE + JSON +"/RentHouse/getRentHouseMatch/";
	
	/**获取用户信息地址*/
	public static final String USER_INFO_URL = SERVER + FILE +JSON + "/EconomicPerson/brokerInfo/";
	
	/**修改个人信息地址*/
	public static final String EDIT_USER_INFO_URL = SERVER + FILE +JSON + "/EconomicPerson/saveInfo/";
	
	/**获取单的list*/
	public static final String DAN_LIST_URL = SERVER + FILE  +JSON + "/SaleRentIntention/SaleRentInfo/";
	/**新建单的获取详细*/
	public static final String Dan_No_SeeHouse_URL = SERVER + FILE +JSON + "/SaleRentIntention/HouseInfo/";
	
	/**房源详情*/
	public static final String SEARCH_HOUSE_DETAIL_URL = SERVER + FILE + JSON + "/SearchHouse/getDetial/";
	
	/**撤单地址<------>延期地址*/
	public static final String Revoke_AND_DELAY_Dan_URL = SERVER + FILE  +JSON + "/SaleRentIntention/Scoring/";
	/**部门列表地址**/
	public static final String STORE_INFO_LIST_URL = SERVER + FILE+JSON + "/StoreInfo/StoreInfoList/";
	/**看房是否满意地址*/
	public static final String DAN_SEEN_HOUSE_URL = SERVER + FILE+JSON + "/SaleRentIntention/Score/";

	/**获取看房满意预约签单状态单的信息地址*/
	public static final String BOOKING_SIGNING_URL = SERVER + FILE+JSON + "/SaleRentIntention/HouseInfo/";
	/**单的终极！---->>>签订完合同！拍照上传！*/
	public static final String DAN_COMPLETE_URL = SERVER + FILE+JSON + "/SaleRentContract/addContract/";
	
	/**生成订单*/
	public static final String CREATE_ORDER_URL = SERVER + FILE + JSON + "/SaleRentIntention/addIntention/";
	/**生成订单*/
	public static final String AMEND_PWD_URL = SERVER + FILE + JSON + "/EconomicPerson/change/";
	
	/**添加售房*/
	public static final String SALE_HOUSE_ADD = SERVER + FILE + JSON + "/SaleHouse/insert/";
	
	/**看房记录*/
	public static final String LEAD_LOOK_RECORD = SERVER + FILE + JSON + "/LeadlookRecord/seeList/";
	
	/**匹配客户*/
	public static final String MATCH_RENT_CUTOMER = SERVER + FILE + JSON + "/RequestRentClient/getMatchClient/";
	
	/**匹配客户*/
	public static final String MATCH_BUY_CUTOMER = SERVER + FILE + JSON + "/RequestBuyClient/getMatchClient/";
	
	/**单关键字查询*/
	public static final String SEARCH_DAN_BY_KEY = SERVER + FILE + JSON + "/SaleRentIntention/searchIntentionForKeyword/";
	
	/**售房配置信息*/
	public static final String GET_SALE_HOUSE_MATCH = SERVER + FILE +JSON + "/SaleHouse/getSaleHouseMatch/";

	/**获取今日任务*/
	public static final String CONTRACT_TIME_URL = SERVER + FILE +JSON + "/SaleContract/contractTime/";
	
	/**件关键字查询*/
	public static final String SEARCH_JIAN_BY_KEY = SERVER + FILE + JSON + "/SaleContract/searchSaleContractForKeyword/";
	
	/**查询我的房源*/
	public static final String MY_HOUSE_RESOURCE_LIST = SERVER + FILE + JSON + "/SearchHouse/getMyHouseList/";
	
	/**更新租房信息*/
	public static final String UPDATE_RENT_HOUSE = SERVER + FILE + JSON + "/RentHouse/modify/";
	
	/**更新售房信息*/
	public static final String UPDATE_SALE_HOUSE = SERVER + FILE + JSON + "/SaleHouse/modify/";
	
	
	
	
	
	
	
	
	
	
	
	/**获取银行列表*/
	public static final String BANK_LIST_URL = SERVER + FILE + JSON + "/LoanBank/bankList/";
	
	/**获取预计完成时间*/
	public static final String GetEstimateCompleteDate_url = SERVER + FILE + JSON + "/LoanPeriod/periodDate/";
	
	/**件----列表*/
	public static final String JIAN_LIST_URL = SERVER + FILE + JSON + "/SaleContract/contractList/";
	
	/**件----时间轴地址获取*/
	public static final String JIAN_TIME_LINE_URL = SERVER + FILE + JSON + "/StandardFlow/flowInfo/";
	
	/**单--删除呢不满意的单的*/
	public static final String DELETE_DISSATISFIED_URL = SERVER + FILE + JSON + "/SaleRentIntention/deleteIntention/";
	
	/**单--删除呢不满意的单的*/
	public static final String CLIENT_MATCH_HOUSE_LIST = SERVER + FILE + JSON + "/SearchHouse/getMatchHouseListByClientId/";
	
	/**公告---列表---数据获取地址*/
	public static final String ANNOUNCE_LIST_URL = SERVER + FILE + JSON + "/AnnounceInfo/getAnnounceList/";
	
	/**公告---列表---数据获取地址*/
	public static final String ANNOUNCE_LIST_CONTENT_URL = SERVER + FILE + JSON + "/AnnounceInfo/getAnnounceDetail/";
	
	
	/**获取服务器上的apk版本号！和下载地址*/
	public static final String UPDATE_APK_URL = SERVER + FILE + JSON + "/Version/version/";
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 *  
	 *  Function:获得客户来源对应码
	 *
	 *  @author Gao XuYang  DateTime 2013-11-27 上午9:31:00
	 *  @param resource
	 *  @return
	 */
	public static String getClientResourceCode(String resource){
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for(int i=0 ; i<clientResource.length ; i++){
			map.put(clientResource[i], i+1);
		}
		return map.get(resource)+"";
	}
	/**
	 * 
	 *  Function:获得房屋类别对应码
	 *
	 *  @author Gao XuYang  DateTime 2013-11-27 上午9:37:53
	 *  @param house_type
	 *  @return
	 */
	public static String getHouseTypeCode(String house_type){
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for(int i=0 ; i<houseType.length ; i++){
			map.put(houseType[i], i+1);
		}
		return map.get(house_type)+"";
	}
	/**
	 * 
	 *  Function:获得租赁价格区间value
	 *
	 *  @author Gao XuYang  DateTime 2013-11-29 上午11:50:37
	 *  @param rent_range
	 *  @return
	 */
	public static String getRentRangeValue(String rent_range){
		HashMap<String, String> map = new HashMap<String, String>();
		for(int i=0 ; i<rentRangeNoAll.length ; i++){
			map.put(rentRangeNoAll[i], rentRangeValue[i]);
		}
		Log.i("AppConfig", "rentRangeValue---------->"+map.get(rent_range));
		return map.get(rent_range)+"";
		
	}
	/**
	 *  
	 *  Function:获得买卖价格区间value
	 *
	 *  @author Gao XuYang  DateTime 2013-12-4 上午11:30:00
	 *  @param sell_range
	 *  @return
	 */
	public static String getSellRangeValue(String sell_range){
		HashMap<String, String> map = new HashMap<String, String>();
		for(int i=0 ; i<sellRangeNoAll.length ; i++){
			map.put(sellRangeNoAll[i], sellRangeValue[i]);
		}
		return map.get(sell_range)+"";
		
	}
	/**
	 * 
	 *  Function:获得面积区间value
	 *
	 *  @author Gao XuYang  DateTime 2013-12-4 下午6:17:52
	 *  @param area_range
	 *  @return
	 */
	public static String getAreaRangeValue(String area_range){
		HashMap<String, String> map = new HashMap<String, String>();
		for(int i=0 ; i<areaNoAll.length ; i++){
			map.put(areaNoAll[i], areaValue[i]);
		}
		return map.get(area_range)+"";
		
	}
	/**
	 * 
	 *  Function:获得朝向码
	 *
	 *  @author Gao XuYang  DateTime 2013-11-29 上午11:54:12
	 *  @param direction
	 *  @return
	 */
	public static String getDirectionValue(String direction){
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for(int i=0 ; i<ori.length ; i++){
			map.put(ori[i], i+1);
		}
		return map.get(direction)+"";
	}

}
