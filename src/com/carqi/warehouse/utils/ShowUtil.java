package com.carqi.warehouse.utils;

import android.content.Context;
import android.widget.Toast;

public class ShowUtil {

	public static void toast(Context context, String info) {
		Toast.makeText(context, info, Toast.LENGTH_SHORT).show();
	}

}
