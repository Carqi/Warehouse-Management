package com.carqi.warehouse.db;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.carqi.warehouse.core.AppConfig;
import com.carqi.warehouse.entity.GoodsEntity;

/**
 * 货品数据库操作类
 * @author Administrator
 * 2014-1-25 下午10:52:47
 */
public class GoodsDBHelper {
	private static final String TAG = GoodsDBHelper.class.getSimpleName();
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
	/**
	 * 获得货物列表
	 * @author Administrator
	 * 2014-2-6 下午3:01:10
	 * @return
	 */
	public List<GoodsEntity> getGoodsList(){
		List<GoodsEntity> list = new ArrayList<GoodsEntity>();
		String goods = AppConfig.DB_GOODS_TABLE;
		String person = AppConfig.DB_BUY_PERSON_TABLE;
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT "+goods+".*,"+person+".name as person_name FROM "+goods);
		sql.append(" join "+person+" on "+goods+".buy_personid="+person+".id");
		Cursor cursor = db.rawQuery(sql.toString(), null);
		while(cursor != null && cursor.moveToNext()){
			GoodsEntity entity = new GoodsEntity();
			entity.setId(cursor.getInt(cursor.getColumnIndex("Id")));
			entity.setName(cursor.getString(cursor.getColumnIndex("name")));
			entity.setModel(cursor.getString(cursor.getColumnIndex("model")));
			entity.setBrand(cursor.getString(cursor.getColumnIndex("brand")));
			entity.setType(cursor.getString(cursor.getColumnIndex("type")));
			entity.setUnit_price(cursor.getString(cursor.getColumnIndex("unit_price")));
			entity.setBuy_num(cursor.getString(cursor.getColumnIndex("buy_num")));
			entity.setNow_num(cursor.getString(cursor.getColumnIndex("now_num")));
			entity.setTotal_price(cursor.getString(cursor.getColumnIndex("total_price")));
			entity.setBuy_personid(cursor.getString(cursor.getColumnIndex("buy_personid")));
			entity.setBuy_person(cursor.getString(cursor.getColumnIndex("person_name")));
			entity.setSupplierid(cursor.getString(cursor.getColumnIndex("supplierid")));
			entity.setBuy_date(cursor.getString(cursor.getColumnIndex("buy_date")));
			entity.setIn_date(cursor.getString(cursor.getColumnIndex("in_date")));
			entity.setRemark(cursor.getString(cursor.getColumnIndex("remark")));
			
			list.add(entity);
		}
		cursor.close();
		return list;
	}
	
	/**
	 * 插入
	 * 
	 * @param list
	 */
	public long insert(GoodsEntity entity) {
			Log.i(TAG, "buy_date------------>"+entity.getBuy_date());
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
			return db.insert(AppConfig.DB_GOODS_TABLE, null, contentValues);
		
	}
}
