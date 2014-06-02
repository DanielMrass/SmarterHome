package com.example.ninjastyle;

import java.util.concurrent.ExecutionException;

import com.example.Tasks.NinjaRGBLEDDataTask;
import com.example.Tasks.NinjaSetRGBLEDTask;

import android.app.Activity;
import android.widget.Toast;

public class NinjaRequest {
	
	private Activity activity;
	
	public NinjaRequest(Activity activity){
		this.activity = activity;
	}
	
	public String getRecentRGBLEDColor(){
		final String url = "https://a.ninja.is/rest/v0/device/1313BB000803_0_0_1007/heartbeat?user_access_token=3e67c92abd7064a289527e161173483958d2b73a";
		NinjaRGBLEDDataTask task = (NinjaRGBLEDDataTask) new NinjaRGBLEDDataTask().execute(url);
		try {
//			Toast.makeText(activity, task.get(), Toast.LENGTH_SHORT).show();
			return task.get();
		} catch (InterruptedException e) {
			e.printStackTrace();
			return "";
		} catch (ExecutionException e) {
			e.printStackTrace();
			return "";
		}
	}
	
	public void setRGBLED(String ledColor) {
		final String url = "https://a.ninja.is/rest/v0/device/1313BB000803_0_0_1007?user_access_token=3e67c92abd7064a289527e161173483958d2b73a";
		NinjaSetRGBLEDTask nrt = (NinjaSetRGBLEDTask) new NinjaSetRGBLEDTask().execute(url, ledColor);
		try {
			Integer result = nrt.get();
			Toast.makeText(activity, "Result: " + result, Toast.LENGTH_SHORT).show();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
}
