package com.example.flickrapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button getImage;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getImage = (Button) findViewById(R.id.btnGetImg);
        image = (ImageView) findViewById(R.id.image);

        getImage.setOnClickListener(new GetImageOnClickListener(this));

//        getImage.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        AsyncFlickrJSONData getImg = new AsyncFlickrJSONData();
//                        AsyncBitmapDownloader downloadImg = new AsyncBitmapDownloader(image);
////                        img.doInBackground();
////                        getImg.execute("https://www.flickr.com/services/feeds/photos_public.gne?tags=trees&format=json");
//                        downloadImg.execute();
////                        image.setImageBitmap(downloadImg.onPostExecute());
//                    }
//                }
        //);
    }

    public void viewList(View view) {
        Intent intent = new Intent(getApplicationContext(), ListActivity.class);
        startActivity(intent);
    }


//    public void downloadImage(View view) { }
}