package com.example.photos.database;

import com.example.photos.utilities.SampleData;

import java.util.List;

public class AppRepository {
    private static final AppRepository ourInstance = new AppRepository();

    public List<PhotoEntity> mPhotos;

    public static AppRepository getInstance() {
        return ourInstance;
    }

    private AppRepository() {
        mPhotos = SampleData.getPhotos();
    }
}
