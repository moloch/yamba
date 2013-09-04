package com.marakana.yamba;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class TimelineActivity extends Activity {

	Cursor cursor;
	ListView listTimeline;
	YambaApplication yamba;
	TextView textTimeline;
	
	static final String[] FROM = {StatusData.C_CREATED_AT, StatusData.C_USER, StatusData.C_TEXT};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		yamba = (YambaApplication) getApplication();
		setContentView(R.layout.timeline);
		textTimeline = (TextView) findViewById(R.id.textTimeline);
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		StatusData statusData = yamba.getStatusData();
		cursor = statusData.getStatusUpdates();
		startManagingCursor(cursor);
		
		String user, text, output;
		while(cursor.moveToNext()){
			user = cursor.getString(cursor.getColumnIndex(statusData.C_USER));
			text = cursor.getString(cursor.getColumnIndex(statusData.C_TEXT));
			output = String.format("%s: %s\n", user, text);
			textTimeline.append(output);
		}
		
	}
	
}
