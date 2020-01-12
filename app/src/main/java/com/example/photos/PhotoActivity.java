package com.example.photos;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class PhotoActivity extends AppCompatActivity {

    @OnClick(R.id.fab)
    void fabClickHandler() {
        Log.i("Photo", "fab was clicked");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);

        Intent intent = getIntent();
    }
}
