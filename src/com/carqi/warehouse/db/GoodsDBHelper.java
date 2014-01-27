package com.carqi.warehouse.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.carqi.warehouse.core.AppConfig;
import com.carqi.warehouse.entity.GoodsEntity;

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
	public void insert(GoodsEntity entity) {
			ContentValues contentValues = new ContentValues();

			contentValues.put("name", entity.getName());
			contentValues.put("model", entity.getModel());
			contentValues.put("brand", entity.getBrand());
			contentValues.put("type", entity.getType());
			contentValues.put("unit_price", entity.getUnit_price());
			contentValues.put("buy_num", entity.getBuy_num());
			contentValues.put("now_num", entity.getNow_num());
			contentValues.put("total_price", entity.getTotal_price());
			contentValues.put("buy_personid", entity.getBuy_personid());
			contentValues.put("supplierid", entity.getSupplierid());
			contentValues.put("buy_date", entity.getBuy_date());
			contentValues.put("in_date", entity.getIn_date());
			contentValues.put("remark", entity.getRemark());
			db.insert(AppConfig.DB_GOODS_TABLE, null, contentValues);
		
	}
}
