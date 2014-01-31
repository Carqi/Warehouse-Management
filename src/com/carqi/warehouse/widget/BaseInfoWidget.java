package com.carqi.warehouse.widget;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.carqi.warehouse.R;
import com.carqi.warehouse.adapter.BasicInfoAdapter;
import com.carqi.warehouse.core.AppConfig;
import com.carqi.warehouse.db.BuyPersonDBHelper;
import com.carqi.warehouse.entity.BuyPersonEntity;
import com.carqi.warehouse.exception.ResponseException;
import com.carqi.warehouse.impl.DataChangeListener;
import com.carqi.warehouse.impl.OnChangedListener;
import com.carqi.warehouse.utils.StringUtils;


 /**
 *  Class Name: BaseInfoWidget.java
 *  重写item
 *  @author Yu Liu  DateTime 2013-11-5 下午2:42:15    
 */
public class BaseInfoWidget extends RelativeLayout implements OnClickListener{
	//private static final String TAG = BaseInfoWidget.class.getSimpleName();
	public static final int SELECTION_TYPE = 0; // 选择类型常量
	public static final int SIMPLETEXT_TYPE = 1; // 编辑类型常量
	public static final int RADIO_TYPE = 2; // 单选类型常量
	public static final int TOGGLE_TYPE = 3; // 开关类型常量
	public static final int TEXTAREA_TYPE = 4; // 编辑域类型常量
	public static final int PHONETEXT_TYPE = 5; //手机号码类型输入框
	private String[] info;
	
	public Context context;
	public BasicInfoAdapter adapter;
	public int position;
	public TextView txtLabel;
	public TextView txtValue;
	public TextView txtYuanLabel;
	public ImageView imgArrow;
	public EditText editValue;
	public RelativeLayout textLayout ;
	public RelativeLayout editLayout;
	public RelativeLayout toggleLayout;
	public RadioGroup radioGroup;
	public RadioButton radioButton1;
	public RadioButton radioButton2;
	/*编辑域*/
	public LinearLayout editAreaLayout;
	public EditText editAreaValue;
	public WeakReference<DataChangeListener> weak;
	public SlideButton on_and_off;
	private int type;
	private SharedPreferences sharedPreference;
	private List<NameValuePair> param = null;
	private List<BuyPersonEntity> buyPersonList;
	private List<Map<String, String>> configList = null;
	private ResponseException responseException;
	private String configType;
	
	private String clientNeed = "";
	
	
	 /**
	 * @param context
	 */
	public BaseInfoWidget(Context _context,int type, int inputType) {
		super(_context);
		this.context = _context;
		this.type = type;
		this.weak = new WeakReference<DataChangeListener>((DataChangeListener) _context);
		LayoutInflater inflater = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.item_layout, this);
		
		txtLabel = (TextView) findViewById(R.id.txt_label);
		txtValue = (TextView) findViewById(R.id.txt_value);
		txtYuanLabel = (TextView) findViewById(R.id.yuan_label);
		editValue = (EditText) findViewById(R.id.edit_value);
		imgArrow = (ImageView)findViewById(R.id.img_arrow_down);
		textLayout = (RelativeLayout) findViewById(R.id.text_layout);
		editLayout = (RelativeLayout) findViewById(R.id.edit_layout);
		toggleLayout = (RelativeLayout) findViewById(R.id.toggle_layout);
		radioGroup = (RadioGroup) findViewById(R.id.radio_group);
		radioButton1 = (RadioButton) findViewById(R.id.radio_button1);
		radioButton2 = (RadioButton) findViewById(R.id.radio_button2);
		on_and_off = (SlideButton) findViewById(R.id.on_and_off);
		
		editAreaLayout = (LinearLayout) findViewById(R.id.edit_area_layout);
		editAreaValue = (EditText) findViewById(R.id.edit_area_value);
		sharedPreference = context.getSharedPreferences("loginok", 0);
		
		setEdit();
		
		if(type == PHONETEXT_TYPE){
			editValue.setInputType(InputType.TYPE_CLASS_PHONE);
			editValue.setFilters(new InputFilter[]{new InputFilter.LengthFilter(11)});
		}
		
		if(inputType == InputType.TYPE_CLASS_NUMBER){
			editValue.setInputType(InputType.TYPE_CLASS_NUMBER);
			editValue.setFilters(new InputFilter[]{new InputFilter.LengthFilter(9)});
			
		}
		
	}
	
	private void setEdit(){
		
		switch (type){
		case SELECTION_TYPE:
			editLayout.setVisibility(View.GONE);
			editAreaLayout.setVisibility(View.GONE);
			radioGroup.setVisibility(View.GONE);
			toggleLayout.setVisibility(View.GONE);
			textLayout.setOnClickListener(this);
			break;
		case SIMPLETEXT_TYPE:
			editAreaLayout.setVisibility(View.GONE);
			textLayout.setVisibility(View.GONE);
			radioGroup.setVisibility(View.GONE);
			toggleLayout.setVisibility(View.GONE);
			editValue.addTextChangedListener(watcher);
			break;
		case PHONETEXT_TYPE:
			editAreaLayout.setVisibility(View.GONE);
			textLayout.setVisibility(View.GONE);
			radioGroup.setVisibility(View.GONE);
			toggleLayout.setVisibility(View.GONE);
			editValue.addTextChangedListener(watcher);
			break;
		case TEXTAREA_TYPE:
			editLayout.setVisibility(View.GONE);
			textLayout.setVisibility(View.GONE);
			radioGroup.setVisibility(View.GONE);
			toggleLayout.setVisibility(View.GONE);
			editAreaValue.addTextChangedListener(watcher);
			break;
		case RADIO_TYPE:
			editLayout.setVisibility(View.GONE);
			editAreaLayout.setVisibility(View.GONE);
			textLayout.setVisibility(View.GONE);
			toggleLayout.setVisibility(View.GONE);
			radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
				
				@Override
				public void onCheckedChanged(RadioGroup group, int checkedId) {
					if(checkedId == radioButton1.getId()){
						radioGroup.setTag(1);
					}else if(checkedId == radioButton2.getId()){
						radioGroup.setTag(2);
					}
					
					weak.get().dataModify(adapter.infoList.get(position).key, String.valueOf(radioGroup.getTag()),null);
					
				}
			});
			break;
		case TOGGLE_TYPE:
			editLayout.setVisibility(View.GONE);
			editAreaLayout.setVisibility(View.GONE);
			textLayout.setVisibility(View.GONE);
			radioGroup.setVisibility(View.GONE);
			on_and_off.SetOnChangedListener(new OnChangedListener() {
				
				@Override
				public void OnChanged(boolean checkState) {
					// TODO Auto-generated method stub
					//checkState为true时代表否，false时代表是
					if(checkState){
						weak.get().dataModify(adapter.infoList.get(position).key, String.valueOf(false),null);
					}else{
						weak.get().dataModify(adapter.infoList.get(position).key, String.valueOf(true),null);
					}
					
				}
			});
			break;
		}
	}
	
	public void setKey(String key){
		txtLabel.setText(key);
	}
	
	public void setValue(String value){
		txtValue.setText(value);
		editValue.setText(value);
		editAreaValue.setText(value);
		if(value != null){
			if(value.equals("2")){
				radioGroup.check(radioButton2.getId());
			}else{
				radioGroup.check(radioButton1.getId());
			}
			//设置开关默认显示
			if(value.equals("1")){
				on_and_off.setState(true);
			}else{
				on_and_off.setState(false);
			}
		}
			
		
		
		
		
	}
	public String getValue(){
		return txtValue.getText().toString();
	}
	
	
	//监听EditText中数据改变
	public TextWatcher watcher = new TextWatcher() {
		
		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
		}
		
		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {
		}
		
		@Override
		public void afterTextChanged(Editable s) {
			// TODO Auto-generated method stub
			BasicInfoAdapter.Info info = adapter.infoList.get(position);
			Log.d("BaseInfoWidget", info.key + "*********" + s.toString());
			weak.get().dataModify(info.key, s.toString(),null);
			
		}
	};
	
	
	 /* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if("类型".equals(StringUtils.deleteBlank(adapter.infoList.get(position).key))){
			info = AppConfig.goodsType;
			showDialog(info);
		}else if("采购人".equals(StringUtils.deleteBlank(adapter.infoList.get(position).key))){
			BuyPersonDBHelper buyPersonDBhelper = new BuyPersonDBHelper(context);
			buyPersonList = buyPersonDBhelper.getBuyPersonList();
			int size = buyPersonList.size();
			info = new String[size];
			for(int i=0;i<size;i++){
				info[i] = buyPersonList.get(i).getName();
			}
			buyPersonDBhelper.dbClose();
			showDialog(info);
			
			
		}else if("出租方式".equals(StringUtils.deleteBlank(adapter.infoList.get(position).key))){
			info = AppConfig.rentType;
			showDialog(info);
		}else if("付款方式".equals(StringUtils.deleteBlank(adapter.infoList.get(position).key))){
			info = AppConfig.payforType;
			showDialog(info);
		}else if("客户来源".equals(StringUtils.deleteBlank(adapter.infoList.get(position).key))){
			info = AppConfig.clientResource;
			showDialog(info);
		}else if("租金范围".equals(StringUtils.deleteBlank(adapter.infoList.get(position).key))){
			if(clientNeed.equals("client_module")){
				info = AppConfig.rentRangeNoAll;
			}else{
				info = AppConfig.rentRange;
			}
			showDialog(info);
		}else if("价格".equals(StringUtils.deleteBlank(adapter.infoList.get(position).key))){
			if(clientNeed.equals("client_module")){
				info = AppConfig.sellRangeNoAll;
			}else{
				info = AppConfig.sellRange;
			}
			showDialog(info);
		}else if("面积".equals(StringUtils.deleteBlank(adapter.infoList.get(position).key))){
			if(clientNeed.equals("client_module")){
				info = AppConfig.areaNoAll;
			}else{
				info = AppConfig.area;
			}
			showDialog(info);
		}else {
			showWheelDialog();
		}
		
	}
	

	public void setConfigType(String type){
		this.configType = type;
	}

	public void setClientNeed(String clientNeed) {
		this.clientNeed = clientNeed;
	}

	//显示dialog
	private void showDialog(final String[] info){
		int length = info.length;
		final boolean[] checkedId = new boolean[length];
		AlertDialog.Builder dialog = new AlertDialog.Builder(context);
		dialog.setIcon(android.R.drawable.ic_dialog_info);
		dialog.setTitle(adapter.infoList.get(position).key);
		if("采购人".equals(StringUtils.deleteBlank(adapter.infoList.get(position).key))){
			
			int checkedItem = 0;
			for(int i=0;i < length;i++){
				if(txtValue.getText().toString().equals(info[i])){
					checkedItem = i;
				}
			}
			dialog.setSingleChoiceItems(info, checkedItem, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					weak.get().dataModify(adapter.infoList.get(position).key, info[which], buyPersonList.get(which).getId());
					txtValue.setText(info[which]);
					dialog.dismiss();
				}
			});
			
			dialog.setPositiveButton("添 加", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					showAddPersonDialog();
					dialog.dismiss();
				
				}

				
			});
		}else{
			int checkedItem = 0;
			for(int i=0;i < length;i++){
				if(txtValue.getText().toString().equals(info[i])){
					checkedItem = i;
				}
			}
			dialog.setSingleChoiceItems(info, checkedItem, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					txtValue.setText(info[which]);
					weak.get().dataModify(adapter.infoList.get(position).key, info[which],""+(which+1));
					dialog.dismiss();
				}
			});
		}
		dialog.create();
		dialog.show();
		
	}
	
	private void showAddPersonDialog() {
		LayoutInflater li=LayoutInflater.from(context);
        //将R.layout.quake_details填充到Layout
        View dialogAddPerson = li.inflate(R.layout.dialog_add_buy_person, null);
		AlertDialog.Builder dialog = new AlertDialog.Builder(context);
		dialog.setTitle("请输入短信内容");
		dialog.setIcon(android.R.drawable.ic_dialog_alert);
		final TextView msmtext = new TextView(context);
		final EditText msmInfo = new EditText(context);
		msmtext.setText("姓名");
		msmInfo.setText("此处是默认输入内容");
		dialog.setView(dialogAddPerson);
		dialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				String strTemp = msmInfo.getText().toString();
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
	
	
	
	//显示滑动式dialog
	private void showWheelDialog(){
		
	}
	
	
	
	public void setYuanVisibel(String text){
		txtYuanLabel.setVisibility(View.VISIBLE);
		txtYuanLabel.setText(text);
	}
	
}
