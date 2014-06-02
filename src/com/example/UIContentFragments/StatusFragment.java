package com.example.UIContentFragments;

import com.example.ninjastyle.NinjaRequest;
import com.example.smarterhome.R;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class StatusFragment extends Fragment {

	private TextView color;
	private TextView temperature;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.status_fragment, null);
		
		color = (TextView) rootView.findViewById(R.id.status_rgbcolor_value);
		NinjaRequest nr = new NinjaRequest(getActivity());
		String col = "#"+nr.getRecentRGBLEDColor();
		color.setText(col);
		color.setBackgroundColor(Color.parseColor(col));
		
		return rootView;
	}
}
