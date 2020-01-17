package com.example.photos.database;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.example.photos.utilities.SampleData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * The AppRepository class contains all of the data access logic of the application
 * It represents "The Single Source of Truth" for data in the app.
 */
public class AppRepository {
    private static AppRepository ourInstance;

    public LiveData<List<PhotoEntity>> mPhotos;
    private AppDatabase mDb;
    // initialise an executor for database operations, we want to execute
    // database ops in a background thread so as not to interfere with the main thread
    private Executor executor = Executors.newSingleThreadExecutor();

    // get an instance
    public static AppRepository getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new AppRepository(context);
        }
        return ourInstance;
    }

    // constructor
    private AppRepository(Context context) {
        // get an instance of the db passing in context for Room
        mDb = AppDatabase.getInstance(context);
        // get all photos from AppDatabase->PhotoDao
        mPhotos = getAllPhotos();
    }

    // inserts the list of PhotoEntites in SampleData into the databse
    public void addSampleData() {
        // executing insert in a background thread
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.photoDao().insertAll(SampleData.getPhotos());
            }
        });
    }

    // Get all the photos from the database
    private LiveData<List<PhotoEntity>> getAllPhotos() {
        // not using an executor here, only needed for write operations
        return mDb.photoDao().getAll();
    }

    // delete all photos from the database
    public void deleteAllPhotos() {
        // again write operation so use background thread
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.photoDao().deleteAll();
            }
        });
    }

    // retrieve photo from the database with a given photo id
    public PhotoEntity getPhotoById(int photoId) {
        return mDb.photoDao().getPhotoById(photoId);
    }

    // inserts a single instance of PhotoEntity into the database
    public void insertPhoto(final PhotoEntity photo) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                // photo is "upserted" if primary key already exists
                mDb.photoDao().insertPhoto(photo);
            }
        });
    }

    // delete a photo from the database
    public void deletePhoto(final PhotoEntity photo) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                mDb.photoDao().deletePhoto(photo);
            }
        });
    }
}
