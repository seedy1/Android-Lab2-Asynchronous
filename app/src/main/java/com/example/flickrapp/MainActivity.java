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
    ImageView image; // image view to display image

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getImage = (Button) findViewById(R.id.btnGetImg);
        image = (ImageView) findViewById(R.id.image);

        getImage.setOnClickListener(new GetImageOnClickListener(this)); // custom on click listener

    }

    public void viewList(View view) { // view list activity in click listener
        Intent intent = new Intent(getApplicationContext(), ListActivity.class);
        startActivity(intent);
    }


}