package com.carqi.warehouse.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.carqi.fragmentbottommenu.R;
import com.carqi.warehouse.adapter.BasicInfoAdapter;
import com.carqi.warehouse.core.AppConfig;
import com.carqi.warehouse.entity.RentClientEntity;
import com.carqi.warehouse.exception.ResponseException;
import com.carqi.warehouse.impl.DataChangeListener;
import com.carqi.warehouse.utils.RegexChk;
import com.carqi.warehouse.utils.ShowUtil;
import com.carqi.warehouse.utils.StringUtils;
import com.carqi.warehouse.widget.BaseInfoWidget;

/**
 * 新建求租客户 Class Name: AddRentClientActivity.java
 * 
 * @author Gao XuYang DateTime 2013-11-13 上午9:39:03
 * @version 1.0
 * @company 长春优狐科技开发有限公司
 */
public class AddInventoryActivity extends BaseActivity implements OnClickListener, OnItemClickListener,
		DataChangeListener {

	private static final String TAG = AddInventoryActivity.class.getSimpleName();

	private ScrollView scrollView;
	private LinearLayout root_linearLayout;
	private LinearLayout rentBaseInfo;
	private LinearLayout rentRequireInfo;
	private LinearLayout showMoreLayout;
	private LinearLayout moreRequireInfo;
	private TextView showMore;
	private ImageView arrowImage;
	private BasicInfoAdapter adapter;
	private List<BasicInfoAdapter.Info> infoList;
	private ArrayList<String> selectedDataList;

	private RentClientEntity rentClientEntity = new RentClientEntity();

	private Context context;
	private ResponseException responseException;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.add_rent_client);
		context = this;
		adapter = new BasicInfoAdapter(this, "rent", "client_module");
		infoList = new ArrayList<BasicInfoAdapter.Info>();
		selectedDataList = new ArrayList<String>();

		init();
		setTitle();
	}

	private void init() {
		scrollView = (ScrollView) this.findViewById(R.id.scroll_view);
		root_linearLayout = (LinearLayout) this.findViewById(R.id.root_linearLayout);
		rentBaseInfo = (LinearLayout) this.findViewById(R.id.rent_base_info);
		rentRequireInfo = (LinearLayout) this.findViewById(R.id.rent_require_info);
		moreRequireInfo = (LinearLayout) this.findViewById(R.id.more_require_info);
		showMoreLayout = (LinearLayout) this.findViewById(R.id.show_more_layout);
		showMore = (TextView) this.findViewById(R.id.show_more);
		

		showMoreLayout.setOnClickListener(this);

		displayClientInfo();
		refreshClientInfo();
	}

	private void setTitle() {
	}

	private void displayClientInfo() {

		infoList.add(new BasicInfoAdapter.Info("姓　　名", BaseInfoWidget.SIMPLETEXT_TYPE));
		infoList.add(new BasicInfoAdapter.Info("性　　别", BaseInfoWidget.RADIO_TYPE));
		infoList.add(new BasicInfoAdapter.Info("手　　机", BaseInfoWidget.PHONETEXT_TYPE));
		infoList.add(new BasicInfoAdapter.Info("Ｑ　　Ｑ", BaseInfoWidget.SIMPLETEXT_TYPE));
		infoList.add(new BasicInfoAdapter.Info("微　　信", BaseInfoWidget.SIMPLETEXT_TYPE));
		infoList.add(new BasicInfoAdapter.Info("客户来源", BaseInfoWidget.SELECTION_TYPE, AppConfig.clientResource[0]));

		infoList.add(new BasicInfoAdapter.Info("房屋类型", BaseInfoWidget.SELECTION_TYPE, AppConfig.houseType[0]));
		infoList.add(new BasicInfoAdapter.Info("区　　域", BaseInfoWidget.SELECTION_TYPE));
		infoList.add(new BasicInfoAdapter.Info("户　　型", BaseInfoWidget.SELECTION_TYPE));
		infoList.add(new BasicInfoAdapter.Info("租金范围", BaseInfoWidget.SELECTION_TYPE));
		infoList.add(new BasicInfoAdapter.Info("配　　置", BaseInfoWidget.SELECTION_TYPE));
		infoList.add(new BasicInfoAdapter.Info("备　　注", BaseInfoWidget.TEXTAREA_TYPE));
		//infoList.add(new BasicInfoAdapter.Info("私　　盘", BaseInfoWidget.TOGGLE_TYPE));

		infoList.add(new BasicInfoAdapter.Info("楼　　层", BaseInfoWidget.SELECTION_TYPE));
		infoList.add(new BasicInfoAdapter.Info("朝　　向", BaseInfoWidget.SELECTION_TYPE));
		adapter.setContentList(infoList);

	}

	private void refreshClientInfo() {
		for (int i = 0; i < infoList.size(); i++) {
			if (i < 6) {
				rentBaseInfo.addView(adapter.getView(i, null, null));
				View dividerView = new View(this);
				dividerView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 1));
				dividerView.setBackgroundResource(R.drawable.dotted_line);
				rentBaseInfo.addView(dividerView);
			} else if (i >= infoList.size() - 2) {

				moreRequireInfo.addView(adapter.getView(i, null, null));
				View dividerView = new View(this);
				dividerView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 1));
				dividerView.setBackgroundResource(R.drawable.dotted_line);
				moreRequireInfo.addView(dividerView);
			} else {
				rentRequireInfo.addView(adapter.getView(i, null, null));
				View dividerView = new View(this);
				dividerView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 1));
				dividerView.setBackgroundResource(R.drawable.dotted_line);
				rentRequireInfo.addView(dividerView);
			}

		}
	}

	@Override
	public void dataModify(String key, String value, String code) {

		BaseInfoWidget item;
		if (StringUtils.deleteBlank(key).equals("姓名")) {
			rentClientEntity.setReq_name(value);
		}
		if (StringUtils.deleteBlank(key).equals("性别")) {
			item = (BaseInfoWidget) rentBaseInfo.findViewWithTag(key);
			rentClientEntity.setSex(value);
		}
		if (StringUtils.deleteBlank(key).equals("手机")) {
			rentClientEntity.setTel(String.valueOf(value));
		}
		if (StringUtils.deleteBlank(key).equals("ＱＱ")) {
			rentClientEntity.setQq(String.valueOf(value));
		}
		if (StringUtils.deleteBlank(key).equals("微信")) {
			rentClientEntity.setWeixin(String.valueOf(value));
		}
		if (StringUtils.deleteBlank(key).equals("客户来源")) {
			//rentClientEntity.setClientResouce(AppConfig.getClientResourceCode(value));
		}
		if (StringUtils.deleteBlank(key).equals("房屋类型")) {
			item = (BaseInfoWidget) rentRequireInfo.findViewWithTag(key);
			item.setValue(value);
			rentClientEntity.setHouseType(AppConfig.getHouseTypeCode(value));
		}
		if (StringUtils.deleteBlank(key).equals("区域")) {

			rentClientEntity.setDistrict(value);
			rentClientEntity.setDistrictCode(code);
		}
		if (StringUtils.deleteBlank(key).equals("户型")) {
			item = (BaseInfoWidget) rentRequireInfo.findViewWithTag(key);
			item.setValue(value);
			String[] values = RegexChk.matcher(value);
			rentClientEntity.setHowRoom(values[0]);
			rentClientEntity.setHowParlor(values[1]);
		}
		if (StringUtils.deleteBlank(key).equals("租金范围")) {
			item = (BaseInfoWidget) rentRequireInfo.findViewWithTag(key);
			item.setValue(value);
			String[] range = spliteRentRange(AppConfig.getRentRangeValue(value));
			if (range[0].equals("500")) {
				rentClientEntity.setPriceUp(range[0]);
				rentClientEntity.setPriceDown(null);
			} else if (range[0].equals("4500")) {
				rentClientEntity.setPriceDown(range[0]);
				rentClientEntity.setPriceUp(null);
			} else {
				rentClientEntity.setPriceDown(range[0]);
				rentClientEntity.setPriceUp(range[1]);
			}
		}
		if (StringUtils.deleteBlank(key).equals("配置")) {
			rentClientEntity.setConfig(value);
			rentClientEntity.setConfigCode(code);
		}

		if (StringUtils.deleteBlank(key).equals("备注")) {
			android.util.Log.i(TAG, "备注---------->" + value);
			rentClientEntity.setRemark(String.valueOf(value));
		}
		
		if(StringUtils.deleteBlank(key).equals("私盘")){
			item = (BaseInfoWidget)rentRequireInfo.findViewWithTag(key);
			if("true".equals(value)){
				rentClientEntity.setIsPublic("0");
			}else{
				rentClientEntity.setIsPublic("1");
			}
		}
		
		if (StringUtils.deleteBlank(key).equals("楼层")) {
			item = (BaseInfoWidget) moreRequireInfo.findViewWithTag(key);
			item.setValue(value);
			String[] values = RegexChk.matcher(value);
			rentClientEntity.setFloor_down(values[0]);
			rentClientEntity.setFloor_up(values[1]);
		}
		if (StringUtils.deleteBlank(key).equals("朝向")) {
			android.util.Log.i(TAG, "朝向------>" + value);
			rentClientEntity.setDirectionCode(AppConfig.getDirectionValue(value));
		}
	}

	private String[] spliteRentRange(String rentRangeValue) {
		String[] temp = rentRangeValue.split("-");
		return temp;

	}

	@SuppressWarnings("deprecation")
	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.show_more_layout:

			String temp = showMore.getText().toString();
			

			break;

		}
	}

	private void check(RentClientEntity rent) {
		BaseInfoWidget infoWidget;
		if (StringUtils.isEmpty(rent.getReq_name())) {
			ShowUtil.toast(context, "姓名不能为空");
			return;
		}
		if (StringUtils.isEmpty(rent.getTel())) {
			ShowUtil.toast(context, "手机不能为空");
			return;
		}
		if (StringUtils.isEmpty(rent.getDistrictCode())) {
			ShowUtil.toast(context, "区域不能为空");
			return;
		}
		if (StringUtils.isEmpty(rent.getHowRoom())) {
			ShowUtil.toast(context, "户型不能为空");
			return;
		}
		if (StringUtils.isEmpty(rent.getSex())) {
			rentClientEntity.setSex("1");

		}
		if (StringUtils.isEmpty(rent.getClientResouce())) {
			infoWidget = (BaseInfoWidget) rentBaseInfo.findViewWithTag("客户来源");
			String temp = infoWidget.getValue();
			rentClientEntity.setClientResouce(AppConfig.getClientResourceCode(temp));
		}
		if (StringUtils.isEmpty(rent.getHouseType())) {
			infoWidget = (BaseInfoWidget) rentRequireInfo.findViewWithTag("房屋类型");
			String temp = infoWidget.getValue();
			rentClientEntity.setHouseType(AppConfig.getHouseTypeCode(temp));
		}
		if (StringUtils.isEmpty(rent.getPriceDown()) && StringUtils.isEmpty(rent.getPriceUp())) {
			ShowUtil.toast(context, "租金范围不能为空");
			return;
		}

		rentClientEntity.setType(1); // 1为新建客户

		addRentClient();

	}

	private void addRentClient() {
		SharedPreferences share = getSharedPreferences("loginok", 0);
		String sessionId = share.getString("sessionid", "");

		HashMap<String, String> data = new HashMap<String, String>();
		data.put("req_name", rentClientEntity.getReq_name());
		data.put("req_sex", rentClientEntity.getSex());
		data.put("req_tel", rentClientEntity.getTel());
		data.put("qq", rentClientEntity.getQq());
		data.put("weixin", rentClientEntity.getWeixin());
		data.put("client_resouce", rentClientEntity.getClientResouce());
		data.put("purpose", rentClientEntity.getHouseType());
		data.put("req_regionid", rentClientEntity.getDistrictCode());
		data.put("how_room", rentClientEntity.getHowRoom());
		data.put("how_parlor", rentClientEntity.getHowParlor());
		data.put("price_up", rentClientEntity.getPriceUp());
		data.put("price_down", rentClientEntity.getPriceDown());
		data.put("remark", rentClientEntity.getRemark());
		data.put("client_type", rentClientEntity.getType() + "");
		data.put("direction", rentClientEntity.getDirectionCode());
		data.put("match_id", rentClientEntity.getConfigCode());
		data.put("floor_down", rentClientEntity.getFloor_down());
		data.put("floor_up", rentClientEntity.getFloor_up());
		data.put("is_public", rentClientEntity.getIsPublic());

		data.put("PHPSESSID", sessionId);

	}

	/**
	 * 
	 * Function:将scrollView移到屏幕的底部
	 * 
	 * @author Gao XuYang DateTime 2013-11-15 上午10:38:42
	 * @param scroll
	 * @param inner
	 */
	public static void scrollToBottom(final View scroll, final View inner) {

		Handler mHandler = new Handler();
		mHandler.post(new Runnable() {
			public void run() {
				if (scroll == null || inner == null) {
					return;
				}

				int offset = inner.getMeasuredHeight() - scroll.getHeight();
				if (offset < 0) {
					offset = 0;
				}

				scroll.scrollTo(0, offset);
			}
		});
	}


	// 去除字符串List中含有default的字符串
	private ArrayList<String> getIntentArrayList(ArrayList<String> dataList) {

		ArrayList<String> tDataList = new ArrayList<String>();
		for (String s : dataList) {
			if (!s.contains("default")) {
				tDataList.add(s);
			}
		}
		return tDataList;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
	}


}
