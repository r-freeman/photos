package com.example.photos;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
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

import static com.example.photos.utilities.Constants.ACTION;
import static com.example.photos.utilities.Constants.DRAWABLE_PATH;
import static com.example.photos.utilities.Constants.PHOTO_ADDED_TO_FAVOURITES;
import static com.example.photos.utilities.Constants.PHOTO_DELETED;
import static com.example.photos.utilities.Constants.PHOTO_ID;

/**
 * PhotoActivity is started when a photo is clicked in the RecyclerView of the MainActivity.
 */
public class PhotoActivity extends AppCompatActivity {
    // define the view model
    private PhotoViewModel mViewModel;
    // we'll need to check if the photos is liked.
    private int favourite;

    // Use Butterknife to bind the ImageView and Fab.
    @BindView(R.id.photo)
    ImageView mImageView;

    @BindView(R.id.fab)
    FloatingActionButton mFab;

    // Use @OnClick from Butternife to set a click listener on the Fab.
    @OnClick(R.id.fab)
    void fabClickHandler(View view) {
        // makes a call to the ViewHolder to toggle favourite attribute in database.
        toggleFavouritePhoto();

        // if the photo was liked then display Snackbar with message.
        if (favourite == 1) {
            Snackbar.make(view, PHOTO_ADDED_TO_FAVOURITES, Snackbar.LENGTH_LONG).show();
        }
    }

    /**
     * Checks if the photo from the PhotoViewModel is liked and updates the Fab icon state
     * as necessary.
     */
    public void toggleFabIcon() {
        favourite = Objects.requireNonNull(mViewModel.mLivePhoto.getValue()).getFavourite();

        if (favourite == 1) {
            mFab.setImageResource(R.drawable.ic_favorite_active);
        } else {
            // inactive by default
            mFab.setImageResource(R.drawable.ic_favorite_inactive);
        }
    }

    // onCreate is the first method called in the activity life cycle so
    // we get everything set up here.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // define the layout and action bar
        setContentView(R.layout.activity_photo);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        // standard Butterknife binding
        ButterKnife.bind(this);
        // initialise the PhotoViewModel
        initViewModel();
    }

    /**
     * Initialises the ViewModel which will observe changes in data and updates the view
     */
    private void initViewModel() {
        mViewModel = ViewModelProviders.of(this)
                .get(PhotoViewModel.class);

        mViewModel.mLivePhoto.observe(this,
                // when something changes in the PhotoEntity
                new Observer<PhotoEntity>() {
                    @Override
                    public void onChanged(PhotoEntity photoEntity) {
                        // change ImageView src etc...
                        onPhotoUpdate(photoEntity);
                    }
                });

        // get the photo id from the MainActivity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            int photoId = extras.getInt(PHOTO_ID);
            // update the PhotoViewModel with the photo with this id
            mViewModel.loadData(photoId);
        }
    }

    /**
     * Called by the observer above and sets the ImageView src, title
     * and toggles the Fab icon state.
     * @param photoEntity
     */
    private void onPhotoUpdate(PhotoEntity photoEntity) {
        mImageView.setImageResource(
                getResources().getIdentifier(DRAWABLE_PATH + photoEntity.getFileName(),
                        null, getPackageName())
        );
        setTitle(photoEntity.getTitle());
        toggleFabIcon();
    }

    /**
     * Inflating the dropdown menu into the appbar layout
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_photo, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Event handler for when an item in the above menu is clicked
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // return home (MainActivity)
                startActivity(new Intent(this, MainActivity.class));
                return true;
            case R.id.action_send_photo:
                onActionSendPhoto();
                return true;
            case R.id.action_delete_photo:
                onActionDeletePhoto();
                Intent intent = new Intent();
                // we'll be sending this back to MainActivity to display a Snackbar message
                intent.putExtra(ACTION, PHOTO_DELETED);
                // set result to OK, the MainActivity is listening for this flag
                setResult(Activity.RESULT_OK, intent);
                // returns to main activity
                finish();
        }

        return false;
    }

    // makes a call to the PhotoViewModel to toggle favourite attribute in database.
    private void toggleFavouritePhoto() {
        mViewModel.toggleFavouritePhoto();
        toggleFabIcon();
    }

    // share the photo with other applications (partially working)
    private void onActionSendPhoto() {
        Uri imageUri = Uri.parse("android.resource://" +
                getPackageName() + "/" + DRAWABLE_PATH + Objects.requireNonNull(mViewModel.mLivePhoto.getValue()).getFileName());
        Intent send = new Intent();
        send.setAction(Intent.ACTION_SEND);
        send.putExtra(Intent.EXTRA_STREAM, imageUri);
        send.setType("image/jpeg");
        startActivity(Intent.createChooser(send, "Send to"));
    }

    // makes a call to the PhotoViewModel to delete a photo
    private void onActionDeletePhoto() {
        mViewModel.deletePhoto();
    }
}
