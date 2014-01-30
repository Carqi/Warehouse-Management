package com.carqi.warehouse.db;
import java.util.List;

import com.carqi.warehouse.core.AppConfig;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper {
	private static final String TAG = "DBHelper";
	private SQLiteDatabase db;
	private DatabaseHelper dbHelper;
	public final static byte[] _writeLock = new byte[0];
	
	
	/** 打开数据库*/
	public void OpenDB(Context context) {
		dbHelper = new DatabaseHelper(context);
		db = dbHelper.getWritableDatabase();
	}
	/** 关闭数据库*/
	public void Close() {
		dbHelper.close();
		if(db!=null){
			db.close();
		}
	}
	/**
	 * 插入
	 * 
	 * @param list
	 * @param table
	 *            表名
	 */
	public void Insert(List<ContentValues> list, String tableName) {
		synchronized (_writeLock) {
			db.beginTransaction();
			try {
				db.delete(tableName, null, null);
				for (int i = 0, len = list.size(); i < len; i++)
					db.insert(tableName, null, list.get(i));
				db.setTransactionSuccessful();
			} finally {
				db.endTransaction();
			}
		}
	}
	
	public DBHelper(Context context) {
		this.dbHelper = new DatabaseHelper(context);
	}
	/**
	 * 用于初始化数据库
	 * 
	 */
	public static class DatabaseHelper extends SQLiteOpenHelper {
		// 定义数据库文件
		private static final String DB_NAME = AppConfig.DB_FILE_NAME;
		// 定义数据库版本
		private static final int DB_VERSION = AppConfig.DB_NOW_VERSION;
		public DatabaseHelper(Context context) {
			super(context, DB_NAME, null, DB_VERSION);
		}

		@Override
		public void onOpen(SQLiteDatabase db) {
			super.onOpen(db);
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			CreateGoodsDb(db);
			Log.i(TAG, "创建【货物表】成功");
			CreateBuyPersonDb(db);
			Log.i(TAG, "创建【创建采购人信息表】成功");
			CreateMemoRandumDB(db);
			Log.i(TAG, "创建【备忘录表】成功");
		}
		
		
		
		private void CreateMemoRandumDB(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			/*
			 * memoName备忘名称
			 * memoDate备忘日期
			 * memoContent备忘内容
			 * User_Name用户名
			 * */
			StringBuilder sb = new StringBuilder();
			sb.append("CREATE TABLE ["+AppConfig.DB_MEMORANDUM_TABLE+"] (");
			sb.append("[Id] INTEGER PRIMARY KEY AUTOINCREMENT, ");
			sb.append("[User_Name] String,");	
			sb.append("[memoName] String,");	
			sb.append("[memoDate] Integer,");	
			sb.append("[memoContent] String)");	
			db.execSQL(sb.toString());
		}

		/**
		 * 创建货物
		 * @param db
		 */
		private void CreateGoodsDb(SQLiteDatabase db) {
			StringBuilder sb = new StringBuilder();
			sb.append("CREATE TABLE ["+AppConfig.DB_GOODS_TABLE+"] (");
			sb.append("[Id] INTEGER PRIMARY KEY AUTOINCREMENT, ");
			
			sb.append("[name] NVARCHAR(50) DEFAULT (''), ");
			sb.append("[model] NVARCHAR(50) DEFAULT (''), "); //型号
			sb.append("[brand] NVARCHAR(50) DEFAULT (''), "); //品牌
			sb.append("[type] INTEGER, ");
			sb.append("[unit_price] NVARCHAR(50) DEFAULT (''),");
			sb.append("[buy_num] INTEGER, "); //购买数量
			sb.append("[now_num] INTEGER, "); //剩余数量
			sb.append("[total_price] NVARCHAR(50) DEFAULT (''),");
			sb.append("[buy_personid] INTEGER,"); //购买人id
			sb.append("[supplierid] INTEGER, ");
			sb.append("[buy_date] VARCHAR DEFAULT (''), "); //购买时间
			sb.append("[in_date] VARCHAR DEFAULT (''), "); //入库时间
			sb.append("[remark] VARCHAR DEFAULT ('')) "); //备注
			db.execSQL(sb.toString());
		}
		
		/**
		 * 创建采购人信息表
		 * @author Administrator
		 * 2014-1-29 下午10:52:12
		 * @param db
		 */
		private void CreateBuyPersonDb(SQLiteDatabase db) {
			StringBuilder sb = new StringBuilder();
			sb.append("CREATE TABLE ["+AppConfig.DB_BUY_PERSON_TABLE+"] (");
			sb.append("[Id] INTEGER PRIMARY KEY AUTOINCREMENT, ");
			sb.append("[name] NVARCHAR(50) DEFAULT (''), ");
			sb.append("[name_all_letter] VARCHAR DEFAULT (''), ");
			sb.append("[name_first_letter] VARCHAR DEFAULT (''), ");
			sb.append("[sex] NVARCHAR(50) DEFAULT (''), ");
			sb.append("[tel] NVARCHAR(50) DEFAULT ('')) ");
			db.execSQL(sb.toString());
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
		}
		
		
		
		
		
	}
}
