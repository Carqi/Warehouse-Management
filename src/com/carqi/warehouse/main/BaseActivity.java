package com.carqi.warehouse.main;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.carqi.warehouse.exception.ResponseException;
import com.carqi.warehouse.WarehouseApplication;;


 /**
 *  Class Name: BaseActivity.java
 *  基础Activity类，实现ActionBar
 *  @author Yu Liu  DateTime 2013-10-25 下午5:16:33    
 */
public class BaseActivity extends Activity{
	
	 /* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	public ResponseException responseException;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		((WarehouseApplication)getApplication()).setActivity(this);
		
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		((WarehouseApplication)getApplication()).popActivity(this);
	}
	
	public void backOnclick(){
		((WarehouseApplication)getApplication()).popActivity(this);
	}
	
	public void clearStacks(Activity activity){
		activity.finish();
		((WarehouseApplication)getApplication()).cleraStacks();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}
	
	
	
	
	
	public void smsDialog(final String mobile){
		AlertDialog.Builder dialog = new AlertDialog.Builder(this);
		dialog.setTitle("请输入短信内容");
		dialog.setIcon(android.R.drawable.ic_dialog_alert);
		final EditText msmInfo = new EditText(this);
		msmInfo.setText("此处是默认输入内容");
		dialog.setView(msmInfo);
		dialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				String strTemp = msmInfo.getText().toString();
//				Log.d("=======================>", strTemp);
				postSMSInfo(mobile, strTemp);
			}
		});
		
		dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
			}
		});
		
		dialog.create();
		dialog.show();
	}
	
	public void postSMSInfo(String mobile, String info){
		Log.i("BaseActivity", "mobile------------>"+mobile);
		Log.i("BaseActivity", "info------------>"+info);
		SmsManager manager = SmsManager.getDefault();
		//若短信内容过长则分条截取短信
		ArrayList<String> texts = manager.divideMessage(info);
		//分条发送短信
		for(String text : texts){
			manager.sendTextMessage(mobile, null, text, null, null);
		}
		
		Toast.makeText(BaseActivity.this, "发送成功", Toast.LENGTH_SHORT).show();
	
	}
}
