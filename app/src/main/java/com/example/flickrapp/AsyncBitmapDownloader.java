package com.example.flickrapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class AsyncBitmapDownloader extends AsyncTask<String, Void, Bitmap> {

    Bitmap bitmap;
    ImageView image;
    private MainActivity act;

//    public AsyncBitmapDownloader(){}
//
    public AsyncBitmapDownloader(MainActivity act){
        this.act = act;
    }

    @Override
    protected Bitmap doInBackground(String... strings) {

        URL url = null;
        try {
            url = new URL(strings[0]);
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();


            InputStream in = new BufferedInputStream(urlConn.getInputStream());
            bitmap = BitmapFactory.decodeStream(in);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);

        ImageView image = act.findViewById(R.id.image);
        image.setImageBitmap(bitmap);

    }


}
