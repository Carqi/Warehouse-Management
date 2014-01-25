package com.carqi.warehouse.bottommenu;

import com.carqi.fragmentbottommenu.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Fragment2 extends Fragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Log.i("Fragment2", "~~~~~~~~~~~~Fragment2~~~~~~~~~~~~~~");
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		Log.i("Fragment2", "~~~~~~~~~~~~onCreateView~~~~~~~~~~~~~~");
		// TODO Auto-generated method stub
//		return super.onCreateView(inflater, container, savedInstanceState);
		return inflater.inflate(R.layout.fragment2, container, false);
	}
	
	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.i("Fragment2", "~~~~~~~~~~~~onPause~~~~~~~~~~~~~~");
	}

	@Override
	public void onStop() {
		super.onStop();
		Log.i("Fragment2", "~~~~~~~~~~~~onStop~~~~~~~~~~~~~~");
	}
	
	@Override
	public void onDestroyView() {
		super.onDestroyView();
		Log.i("Fragment2", "~~~~~~~~~~~~onDestroyView~~~~~~~~~~~~~~");
	}
	
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.i("Fragment2", "~~~~~~~~~~~~onDestroy~~~~~~~~~~~~~~");
	}
	
}
