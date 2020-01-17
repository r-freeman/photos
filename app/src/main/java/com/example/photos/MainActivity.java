package com.example.photos;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.photos.database.PhotoEntity;
import com.example.photos.ui.PhotosAdapter;
import com.example.photos.viewmodel.MainViewModel;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.photos.utilities.Constants.ACTION;
import static com.example.photos.utilities.Constants.PHOTO_DELETED;

/**
 * MainActivity in the entry point of the application.
 */
public class MainActivity extends AppCompatActivity {
    // Use Butterknife to bind the RecyclerView component to this view
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    // init variables
    private List<PhotoEntity> photosData = new ArrayList<>();
    private PhotosAdapter mAdapter;
    private MainViewModel mViewModel;

    // onCreate is the first method called in the activity life cycle so
    // we get everything set up here.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // define the layout and action bar
        setContentView(R.layout.activity_main);
        setSupportActionBar(findViewById(R.id.toolbar_main));

        // standard Butterknife binding
        ButterKnife.bind(this);
        // set up the RecyclerView first and then ViewModel
        initRecyclerView();
        initViewModel();
    }

    /**
     * Initialises the ViewModel which will observe changes in data and updates the view
     */
    private void initViewModel() {
        // defined the observer
        final Observer<List<PhotoEntity>> photosObserver =
                new Observer<List<PhotoEntity>>() {
                    // when something changes in the PhotoEntity data
                    @Override
                    public void onChanged(List<PhotoEntity> photoEntities) {
                        // clear all photos from the view
                        photosData.clear();
                        // add photos from database to view
                        photosData.addAll(photoEntities);

                        if (mAdapter == null) {
                            // define the adapter and set RecyclerView to use it
                            mAdapter = new PhotosAdapter(photosData, MainActivity.this);
                            mRecyclerView.setAdapter(mAdapter);
                        } else {
                            // otherwise notify the adapter of a change.
                            mAdapter.notifyDataSetChanged();
                        }
                    }
                };
        mViewModel = ViewModelProviders.of(this)
                .get(MainViewModel.class);

        mViewModel.mPhotos.observe(this, photosObserver);
    }

    /**
     * Initialises the RecyclerView component
     */
    private void initRecyclerView() {
        // using StaggeredGridLayoutManager to define how items in the RecyclerView are displayed
        // we are displaying 2 images per column in a vertical orientation
        StaggeredGridLayoutManager layoutManager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
    }

    /**
     * Listens for an activity result from the previous activity.
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                // get extras from the intent if any
                Bundle extras = data != null ? data.getExtras() : null;
                if (extras != null) {
                    // find the view to bind the Snackbar to
                    View view = findViewById(android.R.id.content);
                    // check if the previous activity set an action string
                    String action = extras.getString(ACTION);

                    if (PHOTO_DELETED.equals(action)) {
                        // photo was deleted let the user know by displaying a snackbar message
                        displaySnackbar(view, PHOTO_DELETED);
                    }
                }
            }
        }
    }

    /**
     * Creates and displays a Snackbar notification to give the user a brief message
     *
     * @param view
     * @param message
     */
    private void displaySnackbar(View view, String message) {
        Snackbar.make(view,
                message, Snackbar.LENGTH_LONG).show();
    }

    /**
     * Inflating the dropdown menu into the appbar layout
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Event handler for when an item in the above menu is clicked
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // get the id of the menu item
        int id = item.getItemId();

        if (id == R.id.action_add_sample_data) {
            // clicked add sample data
            addSampleData();
            // must return true here
            return true;
        } else if (id == R.id.action_delete_all) {
            // clicked delete all photos
            deleteAllPhotos();
            // must return true here
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * These methods start a chain of calls to
     *      MainViewModel->AppRepository->AppDatabase->PhotoDao
     */
    private void addSampleData() {
        mViewModel.addSampleData();
    }

    private void deleteAllPhotos() {
        mViewModel.deleteAllPhotos();
    }
}
