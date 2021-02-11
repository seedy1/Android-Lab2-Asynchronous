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

public class AsyncFlickrJSONDataForList extends AsyncTask<String, Void, JSONObject> {

    JSONObject jsonObject;
    MyAdapter adp;

    public AsyncFlickrJSONDataForList(MyAdapter adp){
        this.adp = adp;
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
            JSONArray urls = jsonObject.getJSONArray("items");

//            for(JSONArray i: urls){
            for(int i=0;i<urls.length();i++){
                JSONObject arr = urls.getJSONObject(i);
                String url = arr.getJSONObject("media").getString("m");
                adp.dd(url);
                Log.i("URL", "Added to adapter url : " + url);
                adp.notifyDataSetChanged();
//                setAdapter(adapter)
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
