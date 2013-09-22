package com.marakana.yamba;

import com.marakana.yamba.StatusData.DbHelper;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class TimelineActivity extends Activity {

	Cursor cursor;
	ListView listTimeline;
	YambaApplication yamba;
	TimelineAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		yamba = (YambaApplication) this.getApplication();
		setContentView(R.layout.timeline);
		// Find your views
		listTimeline = (ListView) findViewById(R.id.listTimeline);
		
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
		adapter = new TimelineAdapter(this, cursor);
		listTimeline.setAdapter(adapter);
	}
	
}
