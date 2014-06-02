package com.example.UIContentFragments;

import java.util.Timer;
import java.util.TimerTask;

import com.example.Callbacks.TemperatureCallback;
import com.example.ninjastyle.NinjaRequest;
import com.example.smarterhome.R;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class StatusFragment extends Fragment implements TemperatureCallback{

	private TextView color;
	private TextView temperature;
	private NinjaRequest nr;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.status_fragment, null);
		
		color = (TextView) rootView.findViewById(R.id.status_rgbcolor_value);
		nr = new NinjaRequest(getActivity());
		String col = "#"+nr.getRecentRGBLEDColor();
		color.setText(col);
		color.setBackgroundColor(Color.parseColor(col));
		
		temperature = (TextView) rootView.findViewById(R.id.status_temp_value);
		
		return rootView;
	}
	
	

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		temperature.setText(nr.getRecentNinjaTemperature());
		final Handler handleTempUpdates = new Handler(){
			@Override
			public void handleMessage(Message msg){
				Bundle bundle = msg.getData();
				if(bundle != null){
					temperature.setText(bundle.getString("temp"));
				}
			}
		};
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				Bundle bundle = new Bundle();
				bundle.putString("temp", nr.getRecentNinjaTemperature());
				Message msg = new Message();
				msg.setData(bundle);
				handleTempUpdates.sendMessage(msg);
			}
		}, 0, 5000);
	}



	@Override
	public void updateTemperature(String value) {
		temperature.setText(value);
		Toast.makeText(getActivity(), "Updated temperature to " + value , Toast.LENGTH_SHORT).show();
	}
}
