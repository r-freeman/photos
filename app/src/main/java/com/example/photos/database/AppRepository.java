package com.example.photos.database;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.photos.utilities.SampleData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppRepository {
    private static AppRepository ourInstance;

    public LiveData<List<PhotoEntity>> mPhotos;
    private AppDatabase mDb;
    private Executor executor = Executors.newSingleThreadExecutor();

    public static AppRepository getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new AppRepository(context);
        }
        return ourInstance;
    }

    private AppRepository(Context context) {
        mDb = AppDatabase.getInstance(context);
        mPhotos = getAllPhotos();
    }

    public void addSampleData() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.photoDao().insertAll(SampleData.getPhotos());
            }
        });
    }

    private LiveData<List<PhotoEntity>> getAllPhotos() {
        return mDb.photoDao().getAll();
    }

    public void deleteAllPhotos() {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.photoDao().deleteAll();
            }
        });
    }

    public PhotoEntity getPhotoById(int photoId) {
        return mDb.photoDao().getPhotoById(photoId);
    }
}
