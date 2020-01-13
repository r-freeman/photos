package com.example.photos.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.photos.database.PhotoEntity;
import com.example.photos.utilities.SampleData;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    public List<PhotoEntity> mPhotos = SampleData.getPhotos();

    public MainViewModel(@NonNull Application application) {
        super(application);
    }
}
