package com.example.photos.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.photos.database.AppRepository;
import com.example.photos.database.PhotoEntity;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    public LiveData<List<PhotoEntity>> mPhotos;
    private AppRepository mRepository;

    public MainViewModel(@NonNull Application application) {
        super(application);
        mRepository = AppRepository.getInstance(application.getApplicationContext());
        mPhotos = mRepository.mPhotos;
    }

    public void addSampleData() {
        mRepository.addSampleData();
    }
}
