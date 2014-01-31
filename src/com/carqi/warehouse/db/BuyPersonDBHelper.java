package com.carqi.warehouse.db;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.carqi.warehouse.core.AppConfig;
import com.carqi.warehouse.entity.BuyPersonEntity;

/**
 * 购买人数据库操作类
 * @author Administrator
 * 2014-1-30 下午5:19:56
 */
public class BuyPersonDBHelper {
	//private static final String TAG = GoodsDBHelper.class.getSimpleName();
	private DBHelper.DatabaseHelper dbHelper;
	private SQLiteDatabase db;
	public final static byte[] _writeLock = new byte[0];

	public BuyPersonDBHelper(Context context) {
		dbHelper = new DBHelper.DatabaseHelper(context);
		db = dbHelper.getWritableDatabase();
	}

	public void dbClose() {
		if (db != null)
			db.close();
	}
	/**
	 * 插入
	 * 
	 * @param list
	 */
	public long insert(BuyPersonEntity entity) {
			ContentValues contentValues = new ContentValues();

			contentValues.put("name", entity.getName());
			contentValues.put("tel", entity.getTel());
			return db.insert(AppConfig.DB_BUY_PERSON_TABLE, null, contentValues);		
	}
	/**
	 * 查询求购人数量
	 * @author Administrator
	 * 2014-1-30 下午5:40:24
	 * @return
	 */
	public int searchBuyPersonCount(){
		String sql = "SELECT * FROM "+AppConfig.DB_BUY_PERSON_TABLE;			
		Cursor cursor = db.rawQuery(sql, null);
		int num = cursor.getCount();
		cursor.close();
		return num;
	}
	/**
	 * 获得采购人列表
	 * @author Administrator
	 * 2014-1-31 下午6:17:42
	 * @return 采购人列表
	 */
	public List<BuyPersonEntity> getBuyPersonList(){
		List<BuyPersonEntity> listBuyPerson = new ArrayList<BuyPersonEntity>();
		String sql = "SELECT * FROM "+AppConfig.DB_BUY_PERSON_TABLE;
		Cursor cursor = db.rawQuery(sql, null);
		while(cursor != null && cursor.moveToNext()){
			BuyPersonEntity entity = new BuyPersonEntity();
			entity.setName(cursor.getString(cursor.getColumnIndex("name")));
			entity.setTel(cursor.getString(cursor.getColumnIndex("tel")));
			
			listBuyPerson.add(entity);
		}
		cursor.close();
		return listBuyPerson;
	}
}
