package com.carqi.warehouse.fragmentbottommenu;

import com.carqi.fragmentbottommenu.R;
import com.carqi.warehouse.main.AddInventoryActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 库存Fragment
 * 
 * @author Administrator 上午12:20:31
 */
public class InventoryFragment extends Fragment implements OnClickListener {
	private Button addInventory;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		init();

		View parentView = inflater.inflate(R.layout.fragment1, container, false);
		Button backBtn = (Button) parentView.findViewById(R.id.button1);
		Button toOtherActivityBtn = (Button) parentView.findViewById(R.id.button2);

		ImageView backBtn1 = (ImageView) parentView.findViewById(R.id.LEFT_BUTTON);
		backBtn1.setVisibility(View.VISIBLE);
		addInventory = (Button) parentView.findViewById(R.id.RIGHT_BUTTON);
		addInventory.setVisibility(View.VISIBLE);
		addInventory.setOnClickListener(this);
		((TextView) parentView.findViewById(R.id.TITLE_TEXT)).setText("库存");

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

		return parentView;
		// return super.onCreateView(, container, savedInstanceState);
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
}
