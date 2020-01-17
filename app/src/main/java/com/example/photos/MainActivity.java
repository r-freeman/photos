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

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    private List<PhotoEntity> photosData = new ArrayList<>();
    private PhotosAdapter mAdapter;
    private MainViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(findViewById(R.id.toolbar_main));

        ButterKnife.bind(this);
        initRecyclerView();
        initViewModel();
    }

    private void initViewModel() {
        final Observer<List<PhotoEntity>> photosObserver =
                new Observer<List<PhotoEntity>>() {
                    @Override
                    public void onChanged(List<PhotoEntity> photoEntities) {
                        photosData.clear();
                        photosData.addAll(photoEntities);

                        if (mAdapter == null) {
                            mAdapter = new PhotosAdapter(photosData, MainActivity.this);
                            mRecyclerView.setAdapter(mAdapter);
                        } else {
                            mAdapter.notifyDataSetChanged();
                        }
                    }
                };
        mViewModel = ViewModelProviders.of(this)
                .get(MainViewModel.class);

        mViewModel.mPhotos.observe(this, photosObserver);
    }

    private void initRecyclerView() {
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

    private void displaySnackbar(View view, String message) {
        Snackbar.make(view,
                message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_add_sample_data) {
            addSampleData();
            return true;
        } else if (id == R.id.action_delete_all) {
            deleteAllPhotos();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void addSampleData() {
        mViewModel.addSampleData();
    }

    private void deleteAllPhotos() {
        mViewModel.deleteAllPhotos();
    }
}
