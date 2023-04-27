package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class FullActivity extends AppCompatActivity {

    private ImageView fullImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full);
    }

    fullImageView = findViewById(R.id.fullImage);

    Intent reciver = getIntent();
    String sourceUrl = reciver.getStringExtra("ImageUrl");

    Glide.with(this).load(sourceUrl).into(fullImageView);


}