package com.carqi.warehouse.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.carqi.warehouse.R;
import com.carqi.warehouse.entity.GoodsEntity;
import com.carqi.warehouse.utils.DateFormatUtils;

public class GoodsAdapter extends BaseAdapter {
	private List<GoodsEntity> goodsList;
	protected LayoutInflater inflater;  
	
	public GoodsAdapter(Context context, List<GoodsEntity> goodsList){
		this.goodsList = goodsList;
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);        
	
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return goodsList.size();
	}

	@Override
	public GoodsEntity getItem(int position) {
		// TODO Auto-generated method stub
		return goodsList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		GoodsEntity entity = goodsList.get(position);
		if(convertView != null){
			viewHolder = (ViewHolder) convertView.getTag();
		}else{
			viewHolder = new ViewHolder();
			convertView  = inflater.inflate(R.layout.item_inventory_list, null);
			viewHolder.goodsName = (TextView) convertView.findViewById(R.id.goods_name);
			viewHolder.nowNum = (TextView) convertView.findViewById(R.id.num);
			viewHolder.inDate = (TextView) convertView.findViewById(R.id.in_date);
		}
		viewHolder.goodsName.setText(entity.getName());
		viewHolder.nowNum.setText(entity.getNow_num());
		viewHolder.inDate.setText(DateFormatUtils.formatDate(entity.getIn_date()));
		convertView.setTag(viewHolder);
		return convertView;
	}
	
	private final class ViewHolder{
		public TextView goodsName;
		public TextView nowNum;
		public TextView inDate;
	}
}
