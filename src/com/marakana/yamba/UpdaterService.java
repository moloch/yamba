package com.marakana.yamba;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class UpdaterService extends Service {

	private static final String TAG = "UpdaterService";
	private static final int DELAY = 60000;
	private boolean runFlag = false;
	private Updater updater;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		this.updater = new Updater();
		Log.d(TAG, "onCreate");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Log.d(TAG, "onStart");
		this.runFlag = true;
		this.updater.start();
		return START_STICKY;
	}

	@Override
	public void onDestroy() {
		Log.d(TAG, "onDestroy");
		this.runFlag = false;
		this.updater.interrupt();
		this.updater = null;
		super.onDestroy();
	}

	private class Updater extends Thread {

		public Updater() {
			super("UpdaterService-Updater");
		}
		
		@Override
		public void run() {
			UpdaterService updaterService = UpdaterService.this;
			while(updaterService.runFlag){
				Log.d(TAG, "Updater running");
				try {
					//TODO: put some code here
					Log.d(TAG, "Updater ran");
					Thread.sleep(updaterService.DELAY);
				} catch (InterruptedException e) {
					updaterService.runFlag = false;
				}
			}
		}
	}

}
