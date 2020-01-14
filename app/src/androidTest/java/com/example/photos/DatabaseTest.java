package com.example.photos;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;
import androidx.test.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.photos.database.AppDatabase;
import com.example.photos.database.PhotoDao;
import com.example.photos.database.PhotoEntity;
import com.example.photos.utilities.SampleData;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class DatabaseTest {
    public static final String TAG = "JUnit";
    private AppDatabase mDb;
    private PhotoDao mDao;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getTargetContext();
        mDb = Room.inMemoryDatabaseBuilder(context,
                AppDatabase.class).build();
        mDao = mDb.photoDao();

        Log.i(TAG, "createDb");
    }

    @After
    public void closeDb() {
        mDb.close();
        Log.i(TAG, "closeDb");
    }

    @Test
    public void createAndRetrievePhotos() {
        mDao.insertAll(SampleData.getPhotos());
        int count = mDao.getPhotosCount();
        Log.i(TAG, "createAndRetrievePhotos: count = " + count);
        assertEquals(SampleData.getPhotos().size(), count);
    }

    @Test
    public void comparePhotoTitles() {
        mDao.insertAll(SampleData.getPhotos());
        PhotoEntity original = SampleData.getPhotos().get(0);
        PhotoEntity fromDb = mDao.getPhotoById(1);
        assertEquals(original.getTitle(), fromDb.getTitle());
    }

    @Test
    public void checkFavouritePhotosCount() {
        mDao.insertAll(SampleData.getPhotos());
        int likedCount = mDao.getFavouritePhotosCount();
        Log.i(TAG, "checkFavouritePhotosCount: count = " + likedCount);
        assertEquals(0, likedCount);
    }

    @Test
    public void compareIds() {
        mDao.insertAll(SampleData.getPhotos());
        PhotoEntity fromDb = mDao.getPhotoById(1);
        assertEquals(1, fromDb.getId());
    }
}
