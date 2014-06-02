package com.example.Tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;


import android.os.AsyncTask;
import android.util.Log;

public class NinjaRGBLEDDataTask extends AsyncTask<String, Integer, String>{
	
	@Override
	protected String doInBackground(String... params) {
		HttpURLConnection connection;
		URL url;
		try {
			url = new URL(params[0]);
			connection = (HttpURLConnection) url.openConnection();
			connection.setDoInput(true);
			connection.setRequestProperty("Content-Type",
					"application/json");
			connection.setRequestMethod("GET");
			
			InputStream is = connection.getInputStream();
			Log.i("GET", "Got Input Stream");
			InputStreamReader isr = new InputStreamReader(is);
			Log.i("GET", "Got Input Stream Reader");
			BufferedReader in = new BufferedReader(isr);
			Log.i("GET", "Got Buffered Stream");
//				BufferedReader in = new BufferedReader(new InputStreamReader(
//						connection.getInputStream()));
				String result = ""
;				String inputLine;

				while ((inputLine = in.readLine()) != null){
					result = result + ("\n" + inputLine);
				}
					
				in.close();
			JSONObject obj = new JSONObject(result);
			JSONObject data = obj.getJSONObject("data");
			String value = (String) data.get("DA");
			connection.disconnect();
			return value;
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return "malformed url";
		} catch (IOException e) {
			e.printStackTrace();
			return "ioexception";
		} catch (JSONException e) {
			e.printStackTrace();
			return "jsonexception";
		}
	}

	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
	}

}
