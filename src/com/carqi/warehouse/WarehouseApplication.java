package com.carqi.warehouse;

import java.util.Stack;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.carqi.warehouse.exception.ResponseException;
import com.carqi.warehouse.utils.EventReceiver;
import com.carqi.warehouse.utils.Log;
import com.carqi.warehouse.utils.LogcatHelper;

public class WarehouseApplication extends Application{

	private static final String tag = Log.getTag(WarehouseApplication.class);
	
	private DisplayMetrics metrics;
    public static EventReceiver connectionReceiver = null;
    public IntentFilter intentFilter;
    public Stack<Activity> stacks = null;
    public Stack<Activity> stackList = null;
    
    
	@Override
	public void onCreate() {
		super.onCreate();
		ResponseException.loadMessageData(this);
		networkRegister();
		//启动严重log信息保存机制。
		LogcatHelper.getInstance(getApplicationContext()).start(); 
		
	}
	
	public void setActivity(Activity activity){
		if(stacks == null){
			stacks = new Stack<Activity>();
		}
		
		stacks.add(activity);
	}
	
	public void addActivityToList(Activity activity){
		if(stackList == null){
			stackList = new Stack<Activity>();
		}
		stackList.add(activity);
	}
	
	public void finishActivity(){
		if(stackList != null){
			for(int i=0;i<stackList.size();i++){
				Activity activity = stackList.get(i);
				activity.finish();
			}
			stackList.clear();
		}
	}
	
	public void popActivity(Activity activity){
		stacks.remove(activity);
		activity.finish();
	}
	
	public void cleraStacks(){
		stacks.clear();
	}
	

	/**
	 * 
	 * <pre>
	 * 注册network广播
	 * </pre>
	 *
	 */
	public void networkRegister() {
		connectionReceiver = new EventReceiver(this.getApplicationContext());
		intentFilter = new IntentFilter(); 
		intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION); 
		registerReceiver(connectionReceiver, intentFilter);
		Log.d(tag, "networkRegister() == 注册network广播");
	}
	
	/**
	 * 
	 * <pre>
	 * 注消network广播
	 * </pre>
	 *
	 */
	public void onDestroy() {
		metrics = null;
		//注消network广播
		if (connectionReceiver != null) {
			Log.d(tag, "unregisterReceiver == 注消network广播");
			unregisterReceiver(connectionReceiver);
			connectionReceiver = null;
		}
		for(int i=0;i<stacks.size();i++){
			Activity activity = stacks.get(i);
			activity.finish();
		}
		
		if(stacks != null){
			stacks.clear();
		}
		if(stackList != null){
			stackList.clear();
		}
		//停止log保存机制
		LogcatHelper.getInstance(getApplicationContext()).stop(); 
		System.exit(0);
	}
	
	public synchronized DisplayMetrics getDisplayMetrics() {
		if (metrics == null) {
			metrics = new DisplayMetrics();
			WindowManager manager = (WindowManager)getSystemService(Context.WINDOW_SERVICE);
	        manager.getDefaultDisplay().getMetrics(metrics);
		}
		return metrics;
	}
	
	public int getWidth() {
        return getDisplayMetrics().widthPixels;
    }

    public int getHeight() {
        return getDisplayMetrics().heightPixels;
    }
    
    public String getSessionId(){
    	SharedPreferences share = getSharedPreferences("loginok", 0);
		String sessionId = share.getString("sessionid", "");
		return sessionId;
    }

}
