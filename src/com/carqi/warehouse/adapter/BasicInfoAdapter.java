package com.carqi.warehouse.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.carqi.warehouse.utils.StringUtils;
import com.carqi.warehouse.widget.BaseInfoWidget;

/**
 * Class Name: BasicInfoAdapter.java 基础adapter
 * 
 * @author Yu Liu DateTime 2013-10-31 上午9:06:36
 * @version 1.0
 * @company 长春优狐科技开发有限公司
 */
public class BasicInfoAdapter extends BaseAdapter {
	private static final String TAG = BasicInfoAdapter.class.getSimpleName();
	
	
	public int position;

	Context context;
	TextView txtLabel;
	TextView txtValue;
	ImageView imgArrow;
	EditText editValue;
	RelativeLayout textLayout ;
	LinearLayout editLayout;
	List<View> viewList ;
	
	public List<Info> infoList;
	public String configType;
	public String clientNeed = "";

	public BasicInfoAdapter(Context context,String configTyp) {
		this.context = context;
		this.viewList = new ArrayList<View>();
		this.configType = configTyp;
	}

	public BasicInfoAdapter(Context context,String configTyp, String clientNeed) {
		this.context = context;
		this.viewList = new ArrayList<View>();
		this.configType = configTyp;
		this.clientNeed = clientNeed;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return infoList.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getItem(int)
	 */
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return infoList.get(position);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getItemId(int)
	 */
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.widget.Adapter#getView(int, android.view.View,
	 * android.view.ViewGroup)
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(viewList == null){
			viewList = new ArrayList<View>();
		}		
		
		
		fillView(viewList.get(position),infoList.get(position));
		return viewList.get(position);
	}

	private void fillView(View view,Info item) {
		
		BaseInfoWidget viewItem = (BaseInfoWidget) view;
		viewItem.adapter = BasicInfoAdapter.this;
		viewItem.setKey(item.key);
		Log.i(TAG, "item.key-------------->"+item.key);
		viewItem.setTag(item.key);
		
		Log.i(TAG, "item.value----------->"+item.value);
		
		viewItem.setValue(item.value);
		if(StringUtils.deleteBlank(item.key).equals("面积") 
				|| StringUtils.deleteBlank(item.key).equals("产证面积")){
			viewItem.editValue.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_FLAG_DECIMAL);
		}
		
		if(StringUtils.deleteBlank(item.key).equals("单价")){
			viewItem.editValue.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_FLAG_DECIMAL);
			viewItem.setYuanVisibel("元");
		}
		
		if(StringUtils.deleteBlank(item.key).equals("总金额")){
			viewItem.editValue.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_FLAG_DECIMAL);
			viewItem.setYuanVisibel("元");
		}
		
		if(StringUtils.deleteBlank(item.key).equals("座栋")){
			viewItem.editValue.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
		}
		if(StringUtils.deleteBlank(item.key).equals("房龄")){
			viewItem.editValue.setInputType(InputType.TYPE_CLASS_NUMBER);
		}
		
		if(StringUtils.deleteBlank(item.key).equals("配置")){
			viewItem.setConfigType(configType);
		}
		if(StringUtils.deleteBlank(item.key).equals("租金范围")){
			viewItem.setClientNeed(clientNeed);
		}
		if(StringUtils.deleteBlank(item.key).equals("价格")){
			viewItem.setClientNeed(clientNeed);
		}
		if(StringUtils.deleteBlank(item.key).equals("面积")){
			viewItem.setClientNeed(clientNeed);
		}
		
	}

	public static class Info {
		public String key;
		public String value;
		public int type;
		public int inputType;

		public Info(String key, int type) {
			this(key, type, "");
		}

		public Info(String key, int type, String value) {
			this(key, type, value,InputType.TYPE_CLASS_TEXT);
		}
		
		public Info(String key, int type, int inputType){
			this.key = key;
			this.type = type;
			this.inputType = inputType;
		}
		
		public Info(String key, int type, String value,int inputType){
			this.key = key;
			this.type = type;
			this.value = value;
			this.inputType = inputType;
		}

		public void setValue(String value) {
			this.value = value;
		}

		public void setKey(String key) {
			this.key = key;
		}
		
		public String getValue(){
			return value;
		}
	}

	 public void setContentList(List<Info> contentList) {
	        infoList = contentList;
	        viewList.clear();
	        for (int i = 0; i < contentList.size(); i++) {
	           BaseInfoWidget viewItem ;
	           viewItem = new BaseInfoWidget(context, contentList.get(i).type, contentList.get(i).inputType);
	           viewItem.position = i;
	           viewList.add(viewItem);
	        }
	        
	        notifyDataSetChanged();
	    }

}
