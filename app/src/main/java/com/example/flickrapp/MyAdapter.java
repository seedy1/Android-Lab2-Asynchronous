package com.example.flickrapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class MyAdapter extends BaseAdapter {

    Vector<String> urls = new Vector<String>();
    Context context;
//    LayoutInflater inflter;


    public MyAdapter(Context context){
        this.context = context;
    }

    public void dd(String url){
        urls.add(url);
    }

    @Override
    public int getCount() {
        return urls.size();
    }

    @Override
    public Object getItem(int position) {
        return urls.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.i("MELO", "todo dodododoooo");
        RecyclerView.ViewHolder holder;

        if(convertView == null){
//            convertView = LayoutInflater.from(context).inflate(R.layout.textviewlayout, null);
            convertView = LayoutInflater.from(context).inflate(R.layout.bitmaplayout, null);
        }

        TextView txt = (TextView) convertView.findViewById(R.id.txt);
        ImageView img = (ImageView) convertView.findViewById(R.id.image);

        String i = (String) getItem(position);
        Log.i("III", "i is "+i);

//        RequestQueue queue = MySingleton.getInstance(viewGroup.getContext()).
//                getRequestQueue();

        // Get a RequestQueue
        RequestQueue queue = MySingleton.getInstance(context).getRequestQueue();
        Response.Listener<Bitmap> rep_listener = response -> {
            img.setImageBitmap(response);
        };
        ImageRequest request = new ImageRequest(
                i, rep_listener, 50,
                70, ImageView.ScaleType.CENTER , Bitmap.Config.RGB_565, null);
        queue.add(request);
//        txt.setText(i);

        return convertView;
    }
}
