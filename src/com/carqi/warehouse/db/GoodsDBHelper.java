package com.carqi.warehouse.db;

import java.util.ArrayList;
import java.util.List;

import com.carqi.warehouse.entity.GoodsEntity;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * 货品数据库操作类
 * @author Administrator
 * 2014-1-25 下午10:52:47
 */
public class GoodsDBHelper {
	//private static final String TAG = GoodsDBHelper.class.getSimpleName();
	private DBHelper.DatabaseHelper dbHelper;
	private SQLiteDatabase db;
	public final static byte[] _writeLock = new byte[0];

	public GoodsDBHelper(Context context) {
		dbHelper = new DBHelper.DatabaseHelper(context);
		db = dbHelper.getWritableDatabase();
	}

	public void dbClose() {
		if (db != null)
			db.close();
	}
/*
	public List<GoodsEntity> getRentClientList(String username){
		List<GoodsEntity> listRentClient = new ArrayList<GoodsEntity>();
		String sql = "SELECT * FROM "+AppConfig.DB_RENT_LIST_TABLE+" WHERE username=? ORDER BY name_first_letter";
		Cursor cursor = db.rawQuery(sql, new String[]{username});
		while(cursor != null && cursor.moveToNext()){
			GoodsEntity rentEntity = new GoodsEntity();
			
			rentEntity.setClientId(cursor.getInt(cursor.getColumnIndex("clientId")));
			rentEntity.setReq_name(cursor.getString(cursor.getColumnIndex("clientName")));
			rentEntity.setFristLetter(cursor.getString(cursor.getColumnIndex("name_first_letter")));
			rentEntity.setAllLetter(cursor.getString(cursor.getColumnIndex("name_all_letter")));
			rentEntity.setTel(cursor.getString(cursor.getColumnIndex("clientTel")));
			
			listRentClient.add(rentEntity);
		}
		cursor.close();
		return listRentClient;
	}
*/	
	/**
	 * 插入
	 * 
	 * @param list
	 */
	public void SynchronyData2DB(List<GoodsEntity> goodsList, String username) {
		List<ContentValues> list = new ArrayList<ContentValues>();

		for (int i = 0, child_len = goodsList.size(); i < child_len; i++) {

			ContentValues contentValues = new ContentValues();

			contentValues.put("name", goodsList.get(i).getName());
			contentValues.put("model", goodsList.get(i).getModel());
			contentValues.put("brand", goodsList.get(i).getBrand());
			contentValues.put("type", goodsList.get(i).getType());
			contentValues.put("unit_price", goodsList.get(i).getUnit_price());
			contentValues.put("buy_num", goodsList.get(i).getBuy_num());
			contentValues.put("now_num", goodsList.get(i).getNow_num());
			contentValues.put("total_price", goodsList.get(i).getTotal_price());
			contentValues.put("buy_personid", goodsList.get(i).getBuy_personid());
			contentValues.put("supplierid", goodsList.get(i).getSupplierid());
			contentValues.put("buy_date", goodsList.get(i).getBuy_date());
			contentValues.put("in_date", goodsList.get(i).getIn_date());
			contentValues.put("remark", goodsList.get(i).getRemark());
			list.add(contentValues);
		}
	}
}
