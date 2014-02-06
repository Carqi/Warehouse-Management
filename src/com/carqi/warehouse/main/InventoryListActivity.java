package com.carqi.warehouse.main;


import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.carqi.warehouse.R;
import com.carqi.warehouse.adapter.GoodsAdapter;
import com.carqi.warehouse.db.GoodsDBHelper;
import com.carqi.warehouse.entity.GoodsEntity;

/**
 * 添加货物
 * 
 * @author Administrator 2014-1-23 下午10:13:35
 */
public class InventoryListActivity extends BaseActivity implements OnClickListener{

	//private static final String TAG = AddInventoryActivity.class.getSimpleName();
	private TextView titleText;
	private ImageView leftBtn;
	private ImageView rightBtn;
	private ListView listView;
	private GoodsAdapter adapter;
	private List<GoodsEntity> goodsList = new ArrayList<GoodsEntity>();
	private GoodsDBHelper goodsDbHelper;
	private Context context;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.inventory_list);
		context = this;

		init();
		setTitle();
	}

	private void init() {
		titleText = (TextView) this.findViewById(R.id.TITLE_TEXT);
		leftBtn = (ImageView) this.findViewById(R.id.LEFT_BUTTON);
		rightBtn = (ImageView) this.findViewById(R.id.RIGHT_BUTTON);
		listView = (ListView) this.findViewById(R.id.listView);
		
	}

	private void setTitle() {
		titleText.setText("货物库存");
		leftBtn.setVisibility(View.VISIBLE);
		leftBtn.setOnClickListener(this);
		
		rightBtn.setVisibility(View.VISIBLE);
		rightBtn.setBackgroundResource(R.drawable.button_save);
		rightBtn.setOnClickListener(this);
	}

	
	@Override
	protected void onStart() {
		super.onStart();
		goodsDbHelper = new GoodsDBHelper(context);
		goodsList = goodsDbHelper.getGoodsList();
		initViewData();
	}

	private void initViewData() {
		adapter = new GoodsAdapter(context, goodsList);
		listView.setAdapter(adapter);
		
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.LEFT_BUTTON:
			finish();
			break;
		case R.id.RIGHT_BUTTON:
			
			break;

		}
	}
}
