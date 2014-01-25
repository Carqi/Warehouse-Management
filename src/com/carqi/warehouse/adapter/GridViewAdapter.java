package com.carqi.warehouse.adapter;

import java.util.List;

import com.carqi.warehouse.R;
import com.carqi.warehouse.entity.ItemEntity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class GridViewAdapter extends BaseAdapter {
	//private Context context;
	private List<ItemEntity> itemList;
	protected LayoutInflater inflater;  

	public GridViewAdapter(Context context, List<ItemEntity> itemList){
		//this.context = context;
		this.itemList = itemList;
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);        
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return itemList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return itemList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		ItemEntity entity = itemList.get(position);
		if (convertView != null) {
			viewHolder = (ViewHolder) convertView.getTag();
		} else {
			viewHolder = new ViewHolder();
			convertView = inflater.inflate(R.layout.night_item, null);

			viewHolder.itemImage = (ImageView) convertView.findViewById(R.id.ItemImage);
			viewHolder.itemText = (TextView) convertView.findViewById(R.id.ItemText);
		}

		viewHolder.itemImage.setBackgroundResource(R.drawable.ic_launcher);
		viewHolder.itemText.setText(entity.getText());
		convertView.setTag(viewHolder);
		return convertView;
	}

	
	private final class ViewHolder{
		public ImageView itemImage;
		public TextView itemText;
	}

}
