package com.example.flickrapp;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class AsyncFlickrJSONData extends AsyncTask<String, Void, JSONObject> {
    JSONObject jsonObject;
    MainActivity act;

    public AsyncFlickrJSONData(MainActivity act){
        this.act = act;
    }

    @Override
    protected JSONObject doInBackground(String... strings) {
        URL url = null;
        try {
            url = new URL(strings[0]);
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();

            InputStream in = new BufferedInputStream(urlConn.getInputStream());
            String s = readStream(in);

            // to remove jsonFlickrFeed in front of response
            String jsonExtract = s.substring("jsonFlickrFeed(".length(), s.length() - 1);
            Log.i("JFL", jsonExtract);

            jsonObject = new JSONObject(jsonExtract);

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    private String readStream(InputStream in) {
        try {
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            int i = in.read();
            while(i != -1) {
                bo.write(i);
                i = in.read();
            }
            return bo.toString();
        } catch (IOException e) {
            return "";
        }
    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        super.onPostExecute(jsonObject);

        try {
//            JSONObject title = jsonObject.getJSONObject();
            String imgPath = jsonObject.getJSONArray("items").getJSONObject(1).getJSONObject("media").getString("m");
            AsyncBitmapDownloader download = new AsyncBitmapDownloader(act);
            download.execute(imgPath);
//            JSONArray imgPath = jsonObject.getJSONArray("items");
//            String imgPath = jsonObject.getString("title");
            Log.i("OBJ", imgPath);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
