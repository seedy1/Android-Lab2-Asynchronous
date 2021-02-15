package com.example.flickrapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class ListActivity extends AppCompatActivity {

    MyAdapter adapter;
    AsyncFlickrJSONDataForList asyList;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        list = (ListView) findViewById(R.id.list);
        adapter = new MyAdapter(ListActivity.this);

//        init AsyncTask and pass the adapter to get the imgage urls
        asyList = new AsyncFlickrJSONDataForList(adapter);
        asyList.execute("https://www.flickr.com/services/feeds/photos_public.gne?tags=trees&format=json");
        list.setAdapter(adapter);
    }
}