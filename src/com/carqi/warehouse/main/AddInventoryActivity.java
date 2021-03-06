package com.carqi.warehouse.main;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.carqi.warehouse.R;
import com.carqi.warehouse.adapter.BasicInfoAdapter;
import com.carqi.warehouse.core.AppConfig;
import com.carqi.warehouse.db.BuyPersonDBHelper;
import com.carqi.warehouse.db.GoodsDBHelper;
import com.carqi.warehouse.entity.BuyPersonEntity;
import com.carqi.warehouse.entity.GoodsEntity;
import com.carqi.warehouse.impl.DataChangeListener;
import com.carqi.warehouse.utils.DateFormatUtils;
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
	private ImageView rightBtn;
	private LinearLayout baseInfoLayout;
	private BasicInfoAdapter adapter;
	private List<BasicInfoAdapter.Info> infoList;

	private GoodsEntity goodsEntity = new GoodsEntity();
	private BuyPersonEntity personEntity = new BuyPersonEntity();

	private GoodsDBHelper goodsDbHelper;
	private BuyPersonDBHelper buyPersonDBHelper;
	private Context context;
	
	private List<BuyPersonEntity> buyPersonList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.add_inventory);
		context = this;
		adapter = new BasicInfoAdapter(this, "rent", "client_module");
		infoList = new ArrayList<BasicInfoAdapter.Info>();

		init();
		setTitle();
	}

	private void init() {
		titleText = (TextView) this.findViewById(R.id.TITLE_TEXT);
		leftBtn = (ImageView) this.findViewById(R.id.LEFT_BUTTON);
		rightBtn = (ImageView) this.findViewById(R.id.RIGHT_BUTTON);
		baseInfoLayout = (LinearLayout) this.findViewById(R.id.rent_base_info);

		buyPersonDBHelper = new BuyPersonDBHelper(context);
		buyPersonList = buyPersonDBHelper.getBuyPersonList();
		displayClientInfo();
		refreshClientInfo();
	}

	private void setTitle() {
		titleText.setText("进货入库");
		leftBtn.setVisibility(View.VISIBLE);
		leftBtn.setOnClickListener(this);
		
		rightBtn.setVisibility(View.VISIBLE);
		rightBtn.setBackgroundResource(R.drawable.button_save);
		rightBtn.setOnClickListener(this);
	}

	private void displayClientInfo() {

		infoList.add(new BasicInfoAdapter.Info("商品名称", BaseInfoWidget.SIMPLETEXT_TYPE));
		infoList.add(new BasicInfoAdapter.Info("型　　号", BaseInfoWidget.SIMPLETEXT_TYPE));
		infoList.add(new BasicInfoAdapter.Info("品　　牌", BaseInfoWidget.SIMPLETEXT_TYPE));
		infoList.add(new BasicInfoAdapter.Info("类　　型", BaseInfoWidget.SELECTION_TYPE, AppConfig.goodsType[0]));
		infoList.add(new BasicInfoAdapter.Info("单　　价", BaseInfoWidget.SIMPLETEXT_TYPE));
		infoList.add(new BasicInfoAdapter.Info("购买数量", BaseInfoWidget.SIMPLETEXT_TYPE, InputType.TYPE_CLASS_NUMBER));
		infoList.add(new BasicInfoAdapter.Info("总  金  额", BaseInfoWidget.SIMPLETEXT_TYPE));
		if(buyPersonList.size()>0){
			infoList.add(new BasicInfoAdapter.Info("采  购  人", BaseInfoWidget.SELECTION_TYPE));
		}else{
			infoList.add(new BasicInfoAdapter.Info("采  购  人", BaseInfoWidget.SIMPLETEXT_TYPE));
		}
		infoList.add(new BasicInfoAdapter.Info("供  应  商", BaseInfoWidget.SIMPLETEXT_TYPE));
		infoList.add(new BasicInfoAdapter.Info("购买时间", BaseInfoWidget.SELECTION_TYPE, DateFormatUtils.getFileName()));
		infoList.add(new BasicInfoAdapter.Info("入库时间", BaseInfoWidget.SELECTION_TYPE, DateFormatUtils.getFileName()));
		infoList.add(new BasicInfoAdapter.Info("备　　注", BaseInfoWidget.TEXTAREA_TYPE));
		adapter.setContentList(infoList);

	}

	private void refreshClientInfo() {
		for (int i = 0; i < infoList.size(); i++) {
			baseInfoLayout.addView(adapter.getView(i, null, null));
			View dividerView = new View(this);
			dividerView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 1));
			dividerView.setBackgroundResource(R.drawable.dotted_line);
			baseInfoLayout.addView(dividerView);

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
			goodsEntity.setType(value);
		}
		if (StringUtils.deleteBlank(key).equals("单价")) {
			goodsEntity.setUnit_price(value);
			if((goodsEntity.getUnit_price()!=null && !goodsEntity.getUnit_price().equals("")) && (goodsEntity.getBuy_num()!=null && !goodsEntity.getBuy_num().equals(""))){
				String totalMoney = getTotalMoney(goodsEntity);
				item = (BaseInfoWidget) baseInfoLayout.findViewWithTag("总  金  额");
				item.setValue(totalMoney);
				goodsEntity.setTotal_price(totalMoney);
			}
		}
		if (StringUtils.deleteBlank(key).equals("购买数量")) {
			goodsEntity.setBuy_num(value);
			goodsEntity.setNow_num(value);
			if((goodsEntity.getUnit_price()!=null && !goodsEntity.getUnit_price().equals("")) && (goodsEntity.getBuy_num()!=null && !goodsEntity.getBuy_num().equals(""))){
				String totalMoney = getTotalMoney(goodsEntity);
				item = (BaseInfoWidget) baseInfoLayout.findViewWithTag("总  金  额");
				item.setValue(totalMoney);
				goodsEntity.setTotal_price(totalMoney);
			}
		}
		if (StringUtils.deleteBlank(key).equals("总金额")) {
			goodsEntity.setTotal_price(value);
		}

		if (StringUtils.deleteBlank(key).equals("采购人")) {
			goodsEntity.setBuy_person(value);
			personEntity.setName(value);
			if(code != null){
				Log.i(TAG, "采购人：code-------------->"+code);
				goodsEntity.setBuy_personid(code);
			}
		}
		if (StringUtils.deleteBlank(key).equals("购买时间")) {
			Log.i(TAG, "buy_date-------------->"+value);
			goodsEntity.setBuy_date(code);
		}
		if (StringUtils.deleteBlank(key).equals("入库时间")) {
			goodsEntity.setIn_date(value);
		}
	}

	private String getTotalMoney(GoodsEntity entity) {
		float price = Float.valueOf(entity.getUnit_price());
		float num = Float.valueOf(entity.getBuy_num());
		float totalMoney = price*num;
		return String.valueOf(totalMoney);
		
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.LEFT_BUTTON:
			finish();
			break;
		case R.id.RIGHT_BUTTON:
			
			check(goodsEntity);
			break;

		}
	}

	private void check(GoodsEntity entity) {
		BaseInfoWidget infoWidget;
		if (StringUtils.isEmpty(entity.getName())) {
			ShowUtil.toast(context, "商品名称不能为空");
			return;
		}
		if (StringUtils.isEmpty(entity.getUnit_price())) {
			ShowUtil.toast(context, "单价不能为空");
			return;
		}
		if (StringUtils.isEmpty(entity.getBuy_num())) {
			ShowUtil.toast(context, "购买数量不能为空");
			return;
		}
		if (StringUtils.isEmpty(entity.getTotal_price())) {
			ShowUtil.toast(context, "总金额不能为空");
			return;
		}
		if (StringUtils.isEmpty(entity.getBuy_person())) {
			ShowUtil.toast(context, "采购人不能为空");
			return;
		}
		if(StringUtils.isEmpty(entity.getType())){
			infoWidget = (BaseInfoWidget) baseInfoLayout.findViewWithTag("类　　型");
			String temp = infoWidget.getValue();
			goodsEntity.setType(temp);
		}
		if(StringUtils.isEmpty(entity.getBuy_date())){
			infoWidget = (BaseInfoWidget) baseInfoLayout.findViewWithTag("购买时间");
			String temp = infoWidget.getValue();
			goodsEntity.setBuy_date(DateFormatUtils.parseDateToLong2(temp)+"");
		}

		if(StringUtils.isEmpty(entity.getIn_date())){
			infoWidget = (BaseInfoWidget) baseInfoLayout.findViewWithTag("入库时间");
			String temp = infoWidget.getValue();
			goodsEntity.setIn_date(DateFormatUtils.parseDateToLong2(temp)+"");
		}
		addGoods();

	}

	private void addGoods() {
		if(StringUtils.isEmpty(goodsEntity.getBuy_personid())){
			goodsEntity.setBuy_personid(buyPersonDBHelper.insert(personEntity)+"");
		}
		
		goodsDbHelper = new GoodsDBHelper(context);
		long result = goodsDbHelper.insert(goodsEntity);
		buyPersonDBHelper.dbClose();
		goodsDbHelper.dbClose();
		if(result != -1){
			ShowUtil.toast(context, "添加成功");
			finish();
		}else{
			ShowUtil.toast(context, "添加失败");
		}
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

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub

	}

}
