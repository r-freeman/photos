package com.example.photos.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.photos.database.AppRepository;
import com.example.photos.database.PhotoEntity;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    public List<PhotoEntity> mPhotos;
    private AppRepository mRepository;

    public MainViewModel(@NonNull Application application) {
        super(application);
        mRepository = AppRepository.getInstance();
        mPhotos = mRepository.mPhotos;
    }
}
