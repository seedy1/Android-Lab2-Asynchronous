package com.example.flickrapp;

import android.view.View;

public class GetImageOnClickListener implements View.OnClickListener {
    MainActivity act;
    public GetImageOnClickListener(MainActivity act){
        this.act = act;
    }

    @Override
    public void onClick(View v) {
        AsyncFlickrJSONData getImg = new AsyncFlickrJSONData(act);
                        getImg.execute("https://www.flickr.com/services/feeds/photos_public.gne?tags=trees&format=json");

    }
}
