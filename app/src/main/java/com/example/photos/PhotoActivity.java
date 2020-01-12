package com.example.photos;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;

import com.example.photos.model.PhotoEntity;
import com.example.photos.utilities.SampleData;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.photos.utilities.Constants.DRAWABLE_PATH;
import static com.example.photos.utilities.Constants.PHOTO_ID;

public class PhotoActivity extends AppCompatActivity {

    private List<PhotoEntity> photosData = new ArrayList<>();

    @BindView(R.id.photo)
    ImageView mImageView;

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
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);

        Bundle extras = getIntent().getExtras();

        photosData.addAll(SampleData.getPhotos());

        if (extras != null) {
            int photoId = extras.getInt(PHOTO_ID);
            Log.i("Photo", "Got " + photoId + " from MainActivity");

            for (PhotoEntity photo : photosData) {
                if (photoId == photo.getId()) {
                    mImageView.setImageResource(
                            getResources().getIdentifier(DRAWABLE_PATH + photo.getFileName(),
                                    null, getPackageName()));
                    setTitle(photo.getTitle());
                    break;
                }
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                startActivity(new Intent(this, MainActivity.class));
                return true;
        }

        return false;
    }
}
