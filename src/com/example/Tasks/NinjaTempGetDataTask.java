package com.example.Tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;


import android.os.AsyncTask;
import android.util.Log;

public class NinjaTempGetDataTask extends AsyncTask<String, Integer, String> {
	
	private String finalResult = "";
	
	@Override
	protected String doInBackground(String... url) {
		try {
			HttpURLConnection connection;
			URL address;
			String inputLine;
			BufferedReader br;
			String result;
			address = new URL(url[0]);
			result = "";
			connection = (HttpURLConnection) address.openConnection();
			connection.setDoInput(true);
			connection.setRequestMethod("GET");
				br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				while((inputLine = br.readLine())!=null){
					result = result + "\n" +inputLine;
				}
				br.close();
				
				JSONObject obj = new JSONObject(result);
				JSONObject data = obj.getJSONObject("data");
				JSONObject lastData = data.getJSONObject("last_data");
				finalResult = Double.toString((Double)lastData.get("DA"));
				Log.i("TEMP", finalResult);
				
				connection.disconnect();
				return this.finalResult;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
	}
	
	
}
