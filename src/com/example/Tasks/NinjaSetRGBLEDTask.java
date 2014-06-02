package com.example.Tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

public class NinjaSetRGBLEDTask extends AsyncTask<String, Integer, Integer>{

	@Override
	protected Integer doInBackground(String... params) {
		HttpURLConnection connection;
		try {
			URL url = new URL(params[0]);
			connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-Type",
					"application/json");
			connection.setRequestMethod("PUT");

			JSONObject data = new JSONObject();
			data.put("DA", params[1]);

			Log.i("RGBLED", data.toString());

			OutputStreamWriter os = new OutputStreamWriter(connection.getOutputStream());
			os.write(data.toString());
			os.flush();

			int HttpResult = connection.getResponseCode();
			Log.i("HTTPResult","ResultCode: "+HttpResult);
			if (HttpResult == HttpURLConnection.HTTP_OK) {
				BufferedReader in = new BufferedReader(new InputStreamReader(
						connection.getInputStream()));
				String inputLine;

				while ((inputLine = in.readLine()) != null)
					System.out.println(inputLine);
				in.close();
			}
			connection.disconnect();
			return HttpResult;
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return -1;
		} catch (IOException e) {
			e.printStackTrace();
			return -1;
		} catch (JSONException e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	protected void onPostExecute(Integer result) {
		super.onPostExecute(result);
	}

}
