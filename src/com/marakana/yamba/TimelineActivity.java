package com.marakana.yamba;

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
	SimpleCursorAdapter adapter;
	
	static final String[] FROM = {StatusData.C_CREATED_AT, StatusData.C_USER, StatusData.C_TEXT};
	static final int[] TO = {R.id.textCreatedAt, R.id.textUser, R.id.textText};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		yamba = (YambaApplication) getApplication();
		setContentView(R.layout.timeline);
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
		
		adapter = new SimpleCursorAdapter(this, R.layout.row, cursor, FROM, TO);
		listTimeline.setAdapter(adapter);
	}
	
}
