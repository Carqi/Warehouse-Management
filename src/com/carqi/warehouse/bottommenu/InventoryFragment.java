package com.carqi.warehouse.bottommenu;

import java.util.ArrayList;
import java.util.List;

import com.carqi.warehouse.R;
import com.carqi.warehouse.adapter.GridViewAdapter;
import com.carqi.warehouse.core.AppConfig;
import com.carqi.warehouse.entity.ItemEntity;
import com.carqi.warehouse.main.AddInventoryActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

/**
 * 库存Fragment
 * 
 * @author Administrator 上午12:20:31
 */
public class InventoryFragment extends Fragment implements OnClickListener {
	private static final String TAG = InventoryFragment.class.getSimpleName();
	private ImageView addInventory;
	private GridView gridview;
	private GridViewAdapter adapter;
	private List<ItemEntity> itemList = new ArrayList<ItemEntity>();
	private Context context;
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = getActivity();
		//isInit = true;
		Log.i(TAG, "~~~~~~~~~~~~onCreate~~~~~~~~~~~~");
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment1, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		
		init();

		
		Button backBtn = (Button) getView().findViewById(R.id.button1);
		Button toOtherActivityBtn = (Button) getView().findViewById(R.id.button2);

		ImageView backBtn1 = (ImageView) getView().findViewById(R.id.LEFT_BUTTON);
		backBtn1.setVisibility(View.VISIBLE);
		addInventory = (ImageView) getView().findViewById(R.id.RIGHT_BUTTON);
		addInventory.setVisibility(View.VISIBLE);
		addInventory.setOnClickListener(this);
		((TextView) getView().findViewById(R.id.TITLE_TEXT)).setText("仓库");

		gridview = (GridView) getView().findViewById(R.id.gridview);
		
		Log.i(TAG, "~~~~~~~~~~~~~~onCreateView~~~~~~~~~~~~~");
			initHomeItem();
		
		
		backBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// if(mListener!=null)mListener.backEvent();
			}
		});

		toOtherActivityBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(getActivity(), OtherActivity1.class);
				startActivity(intent);
			}
		});
	}
	
	private void initHomeItem() {
		int size = AppConfig.HomeItem.length;
		for(int i=0 ; i<size ; i++){
			ItemEntity entity = new ItemEntity();
			entity.setText(AppConfig.HomeItem[i]);
			entity.setDrawable(AppConfig.HomeItemImage[i]);
			itemList.add(entity);
		}
		
		adapter = new GridViewAdapter(context, itemList);
		// 添加并且显示
		gridview.setAdapter(adapter);
		gridview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = null;
				switch (position) {
				case 1:
					intent = new Intent(context, AddInventoryActivity.class);
					break;

				default:
					break;
				}
				startActivity(intent);
				
			}
		});
	}

	private void init() {

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.RIGHT_BUTTON:
			Intent intent = new Intent(getActivity(), AddInventoryActivity.class);
			startActivity(intent);
			break;
		}

	}
	
	@Override
	public void onDestroyView() {
		super.onDestroyView();
		itemList.removeAll(itemList);
		Log.i(TAG, "~~~~~~~~~~~~~~onDestroyView~~~~~~~~~~~~~");
	}
}
