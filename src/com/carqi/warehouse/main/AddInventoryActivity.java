package com.carqi.warehouse.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import android.content.Context;
import android.content.SharedPreferences;
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

import com.carqi.warehouse.R;
import com.carqi.warehouse.adapter.BasicInfoAdapter;
import com.carqi.warehouse.core.AppConfig;
import com.carqi.warehouse.entity.GoodsEntity;
import com.carqi.warehouse.exception.ResponseException;
import com.carqi.warehouse.impl.DataChangeListener;
import com.carqi.warehouse.utils.RegexChk;
import com.carqi.warehouse.utils.ShowUtil;
import com.carqi.warehouse.utils.StringUtils;
import com.carqi.warehouse.widget.BaseInfoWidget;

/**
 * 添加货物
 * 
 * @author Administrator 2014-1-23 下午10:13:35
 */
public class AddInventoryActivity extends BaseActivity implements OnClickListener, OnItemClickListener,
		DataChangeListener {

	private static final String TAG = AddInventoryActivity.class.getSimpleName();
	private TextView titleText;
	private ImageView leftBtn;
	private ScrollView scrollView;
	private LinearLayout rentBaseInfo;
	private BasicInfoAdapter adapter;
	private List<BasicInfoAdapter.Info> infoList;
	private ArrayList<String> selectedDataList;

	private GoodsEntity goodsEntity = new GoodsEntity();

	private Context context;
	private ResponseException responseException;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.add_inventory);
		context = this;
		adapter = new BasicInfoAdapter(this, "rent", "client_module");
		infoList = new ArrayList<BasicInfoAdapter.Info>();
		selectedDataList = new ArrayList<String>();

		init();
		setTitle();
	}

	private void init() {
		titleText = (TextView) this.findViewById(R.id.TITLE_TEXT);
		leftBtn = (ImageView) this.findViewById(R.id.LEFT_BUTTON);
		scrollView = (ScrollView) this.findViewById(R.id.scroll_view);
		rentBaseInfo = (LinearLayout) this.findViewById(R.id.rent_base_info);

		displayClientInfo();
		refreshClientInfo();
	}

	private void setTitle() {
		titleText.setText("进货入库");
		leftBtn.setVisibility(View.VISIBLE);
		leftBtn.setOnClickListener(this);
	}

	private void displayClientInfo() {

		infoList.add(new BasicInfoAdapter.Info("商品名称", BaseInfoWidget.SIMPLETEXT_TYPE));
		infoList.add(new BasicInfoAdapter.Info("型　　号", BaseInfoWidget.SIMPLETEXT_TYPE));
		infoList.add(new BasicInfoAdapter.Info("品　　牌", BaseInfoWidget.SIMPLETEXT_TYPE));
		infoList.add(new BasicInfoAdapter.Info("类　　型", BaseInfoWidget.SELECTION_TYPE, AppConfig.goodsType[0]));
		infoList.add(new BasicInfoAdapter.Info("单　　价", BaseInfoWidget.SIMPLETEXT_TYPE));
		infoList.add(new BasicInfoAdapter.Info("购买数量", BaseInfoWidget.SELECTION_TYPE, AppConfig.clientResource[0]));

		infoList.add(new BasicInfoAdapter.Info("总  金  额", BaseInfoWidget.SELECTION_TYPE));
		infoList.add(new BasicInfoAdapter.Info("采  购  人", BaseInfoWidget.SELECTION_TYPE));
		infoList.add(new BasicInfoAdapter.Info("供  应  商", BaseInfoWidget.SELECTION_TYPE));
		infoList.add(new BasicInfoAdapter.Info("购买时间", BaseInfoWidget.SELECTION_TYPE));
		infoList.add(new BasicInfoAdapter.Info("入库时间", BaseInfoWidget.SELECTION_TYPE));
		infoList.add(new BasicInfoAdapter.Info("备　　注", BaseInfoWidget.TEXTAREA_TYPE));
		adapter.setContentList(infoList);

	}

	private void refreshClientInfo() {
		for (int i = 0; i < infoList.size(); i++) {
			rentBaseInfo.addView(adapter.getView(i, null, null));
			View dividerView = new View(this);
			dividerView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 1));
			dividerView.setBackgroundResource(R.drawable.dotted_line);
			rentBaseInfo.addView(dividerView);

		}
	}

	@Override
	public void dataModify(String key, String value, String code) {

		BaseInfoWidget item;
		if (StringUtils.deleteBlank(key).equals("商品名称")) {
			goodsEntity.setName(value);
		}
		if (StringUtils.deleteBlank(key).equals("型号")) {
			goodsEntity.setModel(value);
		}
		if (StringUtils.deleteBlank(key).equals("品牌")) {
			goodsEntity.setBrand(value);
		}
		if (StringUtils.deleteBlank(key).equals("类型")) {
			goodsEntity.setType(1);
		}
		if (StringUtils.deleteBlank(key).equals("单价")) {
			goodsEntity.setUnit_price(Float.valueOf(value));
		}
		if (StringUtils.deleteBlank(key).equals("客户来源")) {
			// rentClientEntity.setClientResouce(AppConfig.getClientResourceCode(value));
		}
		if (StringUtils.deleteBlank(key).equals("房屋类型")) {
			item = (BaseInfoWidget) rentBaseInfo.findViewWithTag(key);
			item.setValue(value);
			rentClientEntity.setHouseType(value);
		}
		if (StringUtils.deleteBlank(key).equals("区域")) {

			rentClientEntity.setDistrict(value);
			rentClientEntity.setDistrictCode(code);
		}
		if (StringUtils.deleteBlank(key).equals("户型")) {
			item = (BaseInfoWidget) rentBaseInfo.findViewWithTag(key);
			item.setValue(value);
			String[] values = RegexChk.matcher(value);
			rentClientEntity.setHowRoom(values[0]);
			rentClientEntity.setHowParlor(values[1]);
		}
		if (StringUtils.deleteBlank(key).equals("租金范围")) {
			item = (BaseInfoWidget) rentBaseInfo.findViewWithTag(key);
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

		if (StringUtils.deleteBlank(key).equals("私盘")) {
			item = (BaseInfoWidget) rentBaseInfo.findViewWithTag(key);
			if ("true".equals(value)) {
				rentClientEntity.setIsPublic("0");
			} else {
				rentClientEntity.setIsPublic("1");
			}
		}

		if (StringUtils.deleteBlank(key).equals("楼层")) {
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
		case R.id.LEFT_BUTTON:
			finish();

			break;

		}
	}

	private void check(GoodsEntity rent) {
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
			infoWidget = (BaseInfoWidget) rentBaseInfo.findViewWithTag("房屋类型");
			String temp = infoWidget.getValue();
			rentClientEntity.setHouseType(temp);
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
