package com.carqi.warehouse.main;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.carqi.warehouse.R;
import com.carqi.warehouse.widget.CalendarView;
import com.carqi.warehouse.widget.CalendarView.OnItemClickListener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class CalendarDialogActivity extends Activity {
	private static final String TAG = "CalendarDialog";
	private CalendarView calendarview = null;
	String from;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.calendar_layout);
		calendarview = (CalendarView) findViewById(R.id.calendar);
		setListener();
		from = getIntent().getStringExtra("from");
	}

	private void setListener() {

		calendarview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void OnItemClick(final Date date) {
				Log.d(TAG, "get date is " + date);
				
				if(from.equals("home")){
					final Intent intent = new Intent(CalendarDialogActivity.this,AddInventoryActivity.class);
					final DateFormat format1;
					format1 = new SimpleDateFormat("yyyy-MM-dd");
					intent.putExtra("date", format1.format(date));
					setResult(2, intent);
					finish();
				}
				
				
			}
		});
	}
}
