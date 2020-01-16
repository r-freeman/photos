package com.example.photos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.photos.database.PhotoEntity;
import com.example.photos.viewmodel.PhotoViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.photos.utilities.Constants.DRAWABLE_PATH;
import static com.example.photos.utilities.Constants.PHOTO_ADDED_TO_FAVOURITES;
import static com.example.photos.utilities.Constants.PHOTO_ID;

public class PhotoActivity extends AppCompatActivity {
    private PhotoViewModel mViewModel;
    private int favourite;

    @BindView(R.id.photo)
    ImageView mImageView;

    @BindView(R.id.fab)
    FloatingActionButton mFab;

    @OnClick(R.id.fab)
    void fabClickHandler(View view) {
        toggleFavouritePhoto();

        if (favourite == 1) {
            Snackbar.make(view, PHOTO_ADDED_TO_FAVOURITES, Snackbar.LENGTH_LONG).show();
        }
    }

    public void toggleFabIcon() {
        favourite = Objects.requireNonNull(mViewModel.mLivePhoto.getValue()).getFavourite();

        if (favourite == 1) {
            mFab.setImageResource(R.drawable.ic_favorite_active);
        } else {
            mFab.setImageResource(R.drawable.ic_favorite_inactive);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);

        initViewModel();
    }

    private void initViewModel() {
        mViewModel = ViewModelProviders.of(this)
                .get(PhotoViewModel.class);

        mViewModel.mLivePhoto.observe(this,
                new Observer<PhotoEntity>() {
                    @Override
                    public void onChanged(PhotoEntity photoEntity) {
                        onPhotoUpdate(photoEntity);
                    }
                });

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int photoId = extras.getInt(PHOTO_ID);
            mViewModel.loadData(photoId);
        }
    }

    private void onPhotoUpdate(PhotoEntity photoEntity) {
        mImageView.setImageResource(
                getResources().getIdentifier(DRAWABLE_PATH + photoEntity.getFileName(),
                        null, getPackageName())
        );
        setTitle(photoEntity.getTitle());
        toggleFabIcon();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_photo, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                startActivity(new Intent(this, MainActivity.class));
                return true;
            case R.id.action_share_photo:
                onActionSharePhoto();
                return true;
            case R.id.action_delete_photo:
                onActionDeletePhoto();
                // set result to OK, the MainActivity is listening for this event
                setResult(Activity.RESULT_OK, new Intent());
                finish();
        }

        return false;
    }

    private void toggleFavouritePhoto() {
        mViewModel.toggleFavouritePhoto();
        toggleFabIcon();
    }

    /**
     * https://developer.android.com/training/sharing/send#send-binary-content
     */
    private void onActionSharePhoto() {

    }

    private void onActionDeletePhoto() {
        mViewModel.deletePhoto();
    }
}
