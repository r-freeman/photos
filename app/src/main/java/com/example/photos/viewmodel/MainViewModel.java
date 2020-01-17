package com.example.photos.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.photos.database.AppRepository;
import com.example.photos.database.PhotoEntity;

import java.util.List;

/**
 * The MainViewModel corresponds to MainActivity, each activity should have its own ViewModel.
 * The ViewModel allows us to observe changes in the database and update view as necessary.
 */
public class MainViewModel extends AndroidViewModel {
    // using LiveData here because we want to observe changes in data state
    // and update the view as necessary.
    public LiveData<List<PhotoEntity>> mPhotos;
    private AppRepository mRepository;

    // constructor
    public MainViewModel(@NonNull Application application) {
        super(application);
        mRepository = AppRepository.getInstance(application.getApplicationContext());
        mPhotos = mRepository.mPhotos;
    }

    // Make a call to AppRepository to add sample data
    public void addSampleData() {
        mRepository.addSampleData();
    }

    // Make a call to AppRepository to delete all photos
    public void deleteAllPhotos() {
        mRepository.deleteAllPhotos();
    }
}
